package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.TypeDetailofTypeRoom;
import com.hotelManager.dtos.request.LogCustomerRequest;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.*;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.*;
import com.hotelManager.services.QLKSLogCustomerService;
import com.hotelManager.services.QLKSRoleService;
import com.hotelManager.utils.DateTimeUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
