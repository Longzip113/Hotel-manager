package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.BookingType;
import com.hotelManager.constants.enums.StatusRoom;
import com.hotelManager.constants.enums.TypeRegister;
import com.hotelManager.dtos.request.RoomRequest;
import com.hotelManager.dtos.responses.QLKSArrangenmentCustomerResponse;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.entities.QLKSRoomArrangementEntity;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.*;
import com.hotelManager.services.QLKSCustomerService;
import com.hotelManager.services.QLKSRegistrationFormService;
import com.hotelManager.services.QLKSRoomService;
import com.hotelManager.utils.DateTimeUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSRoomServiceImpl implements QLKSRoomService {

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;

    @Autowired
    QLKSDetailTypeRoomRepository qlksDetailTypeRoomRepository;

    @Autowired
    QLKSRegistrationFormRepository qlksRegistrationFormRepository;

    @Autowired
    QLKSRegistrationFormService qlksRegistrationFormService;

    @Autowired
    QLKSRoomArrangementRepository qlksRoomArrangementRepository;

    @Autowired
    QLKSCustomerService qlksCustomerService;

    @Override
    public QLKSRoomModel save(RoomRequest addRoomRequest) throws HotelManagerException {

        if (qlksRoomRepository.getByNameRoom(addRoomRequest.getNameRoom()).isPresent()) {
            log.error("Name room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_ALREADY_EXISTED);
        }

        if (qlksTypeRoomRepository.getById(addRoomRequest.getIdTypeRoom()).isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        QLKSRoomEntity qlksRoomEntity = QLKSRoomEntity.builder()
                .description(addRoomRequest.getDescription())
                .idTypeRoom(addRoomRequest.getIdTypeRoom())
                .nameRoom(addRoomRequest.getNameRoom())
                .status(StatusRoom.NOT_BOOKED_YET.getValue())
                .isDelete(Boolean.FALSE).build();

        String id = qlksRoomRepository.save(qlksRoomEntity);
        getDetailRoom(id);

        return getDetailRoom(id);
    }

    @Override
    public void deleteRoom(String idRoom) throws HotelManagerException {

        if (qlksRoomRepository.getByIdRoom(idRoom).isEmpty()) {
            log.error("idRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        qlksRoomRepository.delete(idRoom);
    }

    @Override
    public void update(RoomRequest roomRequest, String idRoom) throws HotelManagerException {

        if (qlksRoomRepository.getByIdRoom(idRoom).isEmpty()) {
            log.error("idRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        if (qlksRoomRepository.getByNameRoom(roomRequest.getNameRoom()).isPresent()) {
            log.error("Name room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_ALREADY_EXISTED);
        }
        if (StringUtils.isNotBlank(roomRequest.getIdTypeRoom())) {
            if (qlksTypeRoomRepository.getById(roomRequest.getIdTypeRoom()).isEmpty()) {
                log.error("idTypeRoom not existed !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
            }
        }

        qlksRoomRepository.update(roomRequest, idRoom);
    }

    @Override
    public List<QLKSRoomModel> getAll(String sortBy, String sortOrder) {

        return qlksRoomRepository.getAll(sortBy, sortOrder).stream().map(item -> {
            try {
                setItemRoomModel(item, DateTimeUtils.getCurrentTime());
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
            return item;
        }).collect(Collectors.toList());
    }

    private void setItemRoomModel(QLKSRoomModel item, Long time) throws HotelManagerException {

        Optional<QLKSRegistrationFormEntity> registrationFormEntity = qlksRegistrationFormRepository.getByIdRoomAndTime(item.getId(),
                time);
        item.setDetails(qlksDetailTypeRoomRepository.getByIdTypeRoom(item.getIdTypeRoom()));
        if (registrationFormEntity.isPresent()) {
            QLKSRegistrationFormEntity entity = registrationFormEntity.get();
            item.setInfoRegistration(qlksRegistrationFormService.getDetail(entity.getId()));

            if(entity.getStatus() == TypeRegister.CHECK_IN.getValue()) {
                item.setStatus(entity.getStatus());
                Optional<QLKSRoomArrangementEntity> arrangementEntity = qlksRoomArrangementRepository.getByIdRegisterAndRoom(
                        entity.getId(), item.getId()
                );

                QLKSArrangenmentCustomerResponse arrangenmentCustomerResponse = QLKSArrangenmentCustomerResponse.builder()
                        .numberOfChild(arrangementEntity.get().getNumberOfChildren()).build();

                List<String> listIdCustomer = List.of(arrangementEntity.get().getIdCustomer().split("/"));
                Integer size = listIdCustomer.size();
                arrangenmentCustomerResponse.setNumberOfPeople(size);
                List<QLKSCustomerEntity> customerEntityList = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    QLKSCustomerEntity customerEntity = qlksCustomerService.getDetail(listIdCustomer.get(i));
                    customerEntityList.add(customerEntity);
                }
                arrangenmentCustomerResponse.setCustomers(customerEntityList);

                item.setInfoCustomerBooking(arrangenmentCustomerResponse);
            } else if (entity.getStatus() == 0) {
                item.setStatus(0);
            }
        } else {
            item.setStatus(-1);
        }
    }


    @Override
    public QLKSRoomModel getDetailRoom(String idRoom) throws HotelManagerException {

        Optional<QLKSRoomModel> result = qlksRoomRepository.getByIdRoom(idRoom);

        if(result.isEmpty()) {
            log.error("IdRoom does not exist !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }
        setItemRoomModel(result.get(), DateTimeUtils.getCurrentTime());

        return result.get();
    }
}
