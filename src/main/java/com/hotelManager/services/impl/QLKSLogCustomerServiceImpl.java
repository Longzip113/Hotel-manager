package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.TypeDetailofTypeRoom;
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
    QLKSDetailTypeRoomRepository qlksDetailTypeRoomRepository;

    @Override
    public void add(LogCustomerRequest request) throws HotelManagerException {

        Optional<QLKSRoomArrangementEntity> arrangementEntity = qlksRoomArrangementRepository
                .getByIdRegisterAndCustomer(request.getIdRegistration(), request.getIdCustomer());

        if (arrangementEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }
        Integer totalPrice = 0;
        if (request.getType() == TypeDetailofTypeRoom.SERVICE.getValue()) {
            Optional<QLKSServiceEntity> serviceEntity = qlksServiceRepository.getById(request.getIdType());
            if (serviceEntity.isEmpty()) {
                log.error("id not existed !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
            }

            totalPrice = serviceEntity.get().getPrice() * request.getQuantity();
        } else {
            Optional<QLKSHotelDeviceEntity> hotelDeviceEntity = qlksHotelDeviceRepository.getById(request.getIdType());
            if (hotelDeviceEntity.isEmpty()) {
                log.error("id not existed !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
            }

            totalPrice = hotelDeviceEntity.get().getPrice() * request.getQuantity();
        }

        QLKSLogCustomerEntity entity = QLKSLogCustomerEntity.builder()
                .idRoom(arrangementEntity.get().getIdRoom())
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

















