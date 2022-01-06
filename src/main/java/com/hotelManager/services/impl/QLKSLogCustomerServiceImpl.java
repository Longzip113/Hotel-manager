package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.TypeDetailofTypeRoom;
import com.hotelManager.dtos.request.ChangeRoomRequest;
import com.hotelManager.dtos.request.LogCustomerRequest;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.dtos.responses.QLKSLogCustomerResponse;
import com.hotelManager.entities.*;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDetailTypeRoomModel;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.*;
import com.hotelManager.services.QLKSLogCustomerService;
import com.hotelManager.services.QLKSRoleService;
import com.hotelManager.utils.DateTimeUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSLogCustomerServiceImpl implements QLKSLogCustomerService {

    @Autowired
    QLKSServiceRepository qlksServiceRepository;

    @Autowired
    QLKSHotelDeviceRepository qlksHotelDeviceRepository;

    @Autowired
    QLKSRegistrationFormRepository qlksRegistrationFormRepository;

    @Autowired
    QLKSLogCustomerRepository qlksLogCustomerRepository;

    @Autowired
    QLKSRoomArrangementRepository qlksRoomArrangementRepository;

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Autowired
    QLKSCustomerRepository customerRepository;

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;



    @Autowired
    QLKSDetailTypeRoomRepository qlksDetailTypeRoomRepository;

    @Override
    public void add(LogCustomerRequest request) throws HotelManagerException {

        Optional<QLKSRoomArrangementEntity> arrangementEntity = qlksRoomArrangementRepository
                .getByIdRegisterAndCustomer(request.getIdRegistration(), request.getIdCustomer());
        List<String> idRooms = Arrays.asList(arrangementEntity.get().getIdRoom().split("/"));

        Optional<QLKSRoomModel> roomModel = qlksRoomRepository.getByIdRoom(idRooms.get(idRooms.size() - 1));


        if (arrangementEntity.isEmpty()) {
            log.error("id not existed ! arrangement");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }
        Integer totalPrice = 0;
        if (request.getType() == TypeDetailofTypeRoom.SERVICE.getValue()) {
            Optional<QLKSServiceEntity> serviceEntity = qlksServiceRepository.getById(request.getIdType());
            if (serviceEntity.isEmpty()) {
                log.error("id not existed ! Service");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
            }
            request.setDescription(String.format("Sử dụng %s - %s: %s", request.getQuantity(), serviceEntity.get().getNameService(), roomModel.get().getNameRoom()));
            totalPrice = serviceEntity.get().getPrice() * request.getQuantity();
        } else {
            Optional<QLKSHotelDeviceEntity> hotelDeviceEntity = qlksHotelDeviceRepository.getById(request.getIdType());
            if (hotelDeviceEntity.isEmpty()) {
                log.error("id not existed ! device");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
            }

            if(hotelDeviceEntity.get().getQuantity() < request.getQuantity()) {
                log.error("Số lượng thiết bị không đủ !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_USE_DEVICE);
            } else {
                hotelDeviceEntity.get().setQuantity(hotelDeviceEntity.get().getQuantity() - request.getQuantity());
                qlksHotelDeviceRepository.update(hotelDeviceEntity.get());
            }

            totalPrice = hotelDeviceEntity.get().getPrice() * request.getQuantity();

            if (request.getType() == 3) {
                request.setDescription(String.format("Thiết bị mất ! %s", roomModel.get().getNameRoom()));
            } else {
                request.setDescription(String.format("Thêm %s - %s: %s", request.getQuantity(), hotelDeviceEntity.get().getNameHotelDevice(), roomModel.get().getNameRoom()));
            }
        }

        QLKSLogCustomerEntity entity = QLKSLogCustomerEntity.builder()
                .idRoom(idRooms.get(idRooms.size() - 1))
                .idCustomer(request.getIdCustomer())
                .idType(request.getIdType())
                .type(request.getType())
                .description(request.getDescription())
                .logTime(DateTimeUtils.getCurrentTime())
                .idRegistrationForm(request.getIdRegistration())
                .quantity(request.getQuantity())
                .totalPrice(totalPrice).build();

        qlksLogCustomerRepository.save(entity);
    }

    @Override
    public List<QLKSLogCustomerResponse> getAll() throws HotelManagerException {
        List<QLKSLogCustomerEntity> entityList = qlksLogCustomerRepository.getAll();
        List<QLKSLogCustomerResponse> result = new ArrayList<>();
        entityList.forEach(item -> {
            try {
                result.add(setDataLogCustomer(item));
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
        });

        return result;
    }

    @Override
    public QLKSLogCustomerResponse getDetail(String id) throws HotelManagerException {
        Optional<QLKSLogCustomerEntity> entity = qlksLogCustomerRepository.getOne(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        return setDataLogCustomer(entity.get());
    }

    @Override
    public void changeRoom(ChangeRoomRequest request) throws HotelManagerException {
        Optional<QLKSRoomModel> roomEntityOld = qlksRoomRepository.getByIdRoom(request.getIdRoomOld());
        if (roomEntityOld.isEmpty()) {
            log.error("idRoom not existed ! id-Old");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        Optional<QLKSRoomModel> roomEntityNew = qlksRoomRepository.getByIdRoom(request.getIdRoomNew());
        if (roomEntityNew.isEmpty()) {
            log.error("idRoom not existed ! id-New");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        Optional<QLKSTypeRoomEntity> typeRoomEntityOld = qlksTypeRoomRepository.getById(roomEntityOld.get().getIdTypeRoom());
        if (typeRoomEntityOld.isEmpty()) {
            log.error("idTypeRoom not existed ! type-Old");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        Optional<QLKSTypeRoomEntity> typeRoomEntityNew = qlksTypeRoomRepository.getById(roomEntityNew.get().getIdTypeRoom());
        if (typeRoomEntityNew.isEmpty()) {
            log.error("idTypeRoom not existed ! type-New");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        Optional<QLKSRoomArrangementEntity> customerByRoom = qlksRoomArrangementRepository.getByIdRegisterAndRoom(request.getIdRegistration()
                , request.getIdRoomOld());
        if (customerByRoom.isEmpty()) {
            log.error("idTypeRoom not existed ! Arrangement room");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }
//Check room start
        Optional<QLKSRegistrationFormEntity> registrationFormEntity = qlksRegistrationFormRepository.getById(request.getIdRegistration());
        Optional<QLKSRegistrationFormEntity> checkRoom = qlksRegistrationFormRepository.checkRoom(request.getIdRoomNew(),
                registrationFormEntity.get().getCheckInDate(), registrationFormEntity.get().getCheckOutDate());

        if (checkRoom.isPresent()) {
            log.error("Phong đã bị đặt !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ADD_ROOM);
        }

//Check room end
        Integer totalPrice = typeRoomEntityNew.get().getPrice() - typeRoomEntityOld.get().getPrice();
        Optional<QLKSRoomArrangementEntity> qlksRoomArrangementEntity = qlksRoomArrangementRepository
                .getByIdRegisterAndRoom(request.getIdRegistration(), request.getIdRoomOld());
        List<String> idCustomers = Arrays.asList(qlksRoomArrangementEntity.get().getIdCustomer().split("/"));

        QLKSLogCustomerEntity entity = QLKSLogCustomerEntity.builder()
                .idRoom(request.getIdRoomOld())
                .idCustomer(idCustomers.get(0))
                .type(4)
                .description("Đổi phòng từ " + roomEntityOld.get().getNameRoom() + " qua " + roomEntityNew.get().getNameRoom())
                .logTime(DateTimeUtils.getCurrentTime())
                .idRegistrationForm(request.getIdRegistration())
                .quantity(1)
                .totalPrice(totalPrice > 0 ? totalPrice : 0).build();

        qlksLogCustomerRepository.save(entity);
        qlksRegistrationFormRepository.updateChangeRoom(request.getIdRegistration(), request.getIdRoomOld(), request.getIdRoomNew());

        String roomNew = String.format("%s/%s", customerByRoom.get().getIdRoom(), request.getIdRoomNew());
        qlksRoomArrangementRepository.updateChangeRoom(request.getIdRegistration(), request.getIdRoomOld(), roomNew);

    }

    private QLKSLogCustomerResponse setDataLogCustomer(QLKSLogCustomerEntity item) throws HotelManagerException {

        QLKSLogCustomerResponse result = QLKSLogCustomerResponse.builder()
                .idLogCustomer(item.getId())
                .type(item.getType())
                .description(item.getDescription())
                .idType(item.getIdType())
                .quantity(item.getQuantity())
                .time(item.getLogTime())
                .totalPrice(item.getTotalPrice())
                .build();
        Optional<QLKSRoomModel> infoRoom = qlksRoomRepository.getByIdRoom(item.getIdRoom());
        if(infoRoom.isPresent()) {
            result.setInfoRoom(infoRoom.get());
        }

        Optional<QLKSCustomerEntity> infoCustomer = customerRepository.getById(item.getIdCustomer());
        if(infoCustomer.isPresent()) {
            result.setInfoCustomer(infoCustomer.get());
        }

        Optional<QLKSRegistrationFormEntity> infoRegistration = qlksRegistrationFormRepository.getById(item.getIdRegistrationForm());
        if (infoRegistration.isPresent()) {
            result.setInfoRegistration(infoRegistration.get());
        }

        if (item.getType() == TypeDetailofTypeRoom.SERVICE.getValue()) {
            Optional<QLKSServiceEntity> itemService = qlksServiceRepository.getById(item.getIdType());
            if (itemService.isPresent()) {
                result.setName(itemService.get().getNameService());
            }
        } else {
            Optional<QLKSHotelDeviceEntity> itemDevice = qlksHotelDeviceRepository.getById(item.getIdType());
            if (itemDevice.isPresent()) {
                result.setName(itemDevice.get().getNameHotelDevice());
            }
        }

        return result;
    }
}

















