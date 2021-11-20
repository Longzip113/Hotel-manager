package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.BookingType;
import com.hotelManager.constants.enums.TypeRegister;
import com.hotelManager.dtos.request.RegistrationRequest;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.dtos.responses.QLKSDelegationResponse;
import com.hotelManager.dtos.responses.QLKSRegistrationResponse;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDelegationModel;
import com.hotelManager.repositories.*;
import com.hotelManager.services.QLKSCustomerService;
import com.hotelManager.services.QLKSRegistrationFormService;
import com.hotelManager.services.QLKSRoleService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSRegistrationFormServiceImpl implements QLKSRegistrationFormService {

    @Autowired
    QLKSCustomerRepository qlksCustomerRepository;

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Autowired
    QLKSEmployeeRepository qlksEmployeeRepository;

    @Autowired
    QLKSRegistrationFormRepository qlksRegistrationFormRepository;

    @Autowired
    QLKSDelegationRepository qlksDelegationRepository;


    @Override
    public List<QLKSRegistrationResponse> getAll() throws HotelManagerException {
        List<QLKSRegistrationFormEntity> listEntites = qlksRegistrationFormRepository.getAll();
        List<QLKSRegistrationResponse> results = new ArrayList<>();

        listEntites.stream().forEach(item -> {

            QLKSRegistrationResponse registrationResponse = QLKSRegistrationResponse.builder()
                    .idRegistration(item.getId())
                    .bookingDate(item.getBookingDate())
                    .checkInDate(item.getCheckInDate())
                    .checkOutDate(item.getCheckOutDate())
                    .intoMoney(item.getIntoMoney())
                    .note(item.getNote())
                    .type(item.getType())
                    .status(item.getStatus())
                    .numberOfAdult(item.getNumberOfAdult())
                    .numberOfChild(item.getNumberOfChild()).build();

            if (StringUtils.isNotBlank(item.getIdCustomer())) {
                List<String> listIdCustomer = List.of(item.getIdCustomer().split("/"));
                registrationResponse.setCustomers(qlksCustomerRepository.getByIds(listIdCustomer));
            }

            if (StringUtils.isNotBlank(item.getIdCustomer())) {
                List<String> listIdRooms = List.of(item.getIdRoom().split("/"));
                registrationResponse.setRooms(qlksRoomRepository.getByIds(listIdRooms));
            }

            if (StringUtils.isNotBlank(item.getIdCustomer())) {
                registrationResponse.setEmployee(qlksEmployeeRepository.getById(item.getIdEmployee()).get());
            }

            results.add(registrationResponse);
        });

        return results;
    }

    @Override
    public void add(RegistrationRequest request) throws HotelManagerException {

        Optional<QLKSDelegationModel> entityDelegation = qlksDelegationRepository.getById(request.getIdDelegation());
        if (entityDelegation.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        String idRooms = String.join("/", request.getIdRoom());

        QLKSRegistrationFormEntity entity = QLKSRegistrationFormEntity.builder()
                .bookingDate(request.getBookingDate())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .idEmployee(request.getIdEmployee())
                .idCustomer(entityDelegation.get().getIdCustomer())
                .intoMoney(request.getIntoMoney())
                .note(request.getNote())
                .idRoom(idRooms)
                .type(request.getType())
                .status(TypeRegister.BOOK_ROOM.getValue())
                .numberOfAdult(entityDelegation.get().getNumberOfPeople())
                .isDelete(Boolean.FALSE)
                .numberOfChild(request.getNumberOfChild()).build();

        if (request.getType() == BookingType.DELEGATION.getValue()) {
            entity.setIdDelegation(request.getIdDelegation());
        }

        qlksRegistrationFormRepository.save(entity);
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        qlksRegistrationFormRepository.delete(id);
    }

    @Override
    public void update(String id, RegistrationRequest request) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        Optional<QLKSDelegationModel> entityDelegation = qlksDelegationRepository.getById(request.getIdDelegation());
        if (entityDelegation.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        String idRooms = String.join("/", request.getIdRoom());

        QLKSRegistrationFormEntity entityUpdate = QLKSRegistrationFormEntity.builder()
                .id(id)
                .bookingDate(request.getBookingDate())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .idEmployee(request.getIdEmployee())
                .idCustomer(entityDelegation.get().getIdCustomer())
                .intoMoney(request.getIntoMoney())
                .note(request.getNote())
                .idRoom(idRooms)
                .type(request.getType())
                .status(TypeRegister.BOOK_ROOM.getValue())
                .numberOfAdult(entityDelegation.get().getNumberOfPeople())
                .isDelete(Boolean.FALSE)
                .numberOfChild(request.getNumberOfChild()).build();

        if (request.getType() == BookingType.DELEGATION.getValue()) {
            entityUpdate.setIdDelegation(request.getIdDelegation());
        }

        qlksRegistrationFormRepository.update(entityUpdate);
    }

    @Override
    public QLKSRegistrationResponse getDetail(String id) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }
        QLKSRegistrationFormEntity item = entity.get();

        QLKSRegistrationResponse registrationResponse = QLKSRegistrationResponse.builder()
                .idRegistration(item.getId())
                .bookingDate(item.getBookingDate())
                .checkInDate(item.getCheckInDate())
                .checkOutDate(item.getCheckOutDate())
                .intoMoney(item.getIntoMoney())
                .note(item.getNote())
                .type(item.getType())
                .status(item.getStatus())
                .numberOfAdult(item.getNumberOfAdult())
                .numberOfChild(item.getNumberOfChild()).build();

        if (StringUtils.isNotBlank(item.getIdCustomer())) {
            List<String> listIdCustomer = List.of(item.getIdCustomer().split("/"));
            registrationResponse.setCustomers(qlksCustomerRepository.getByIds(listIdCustomer));
        }

        if (StringUtils.isNotBlank(item.getIdCustomer())) {
            List<String> listIdRooms = List.of(item.getIdRoom().split("/"));
            registrationResponse.setRooms(qlksRoomRepository.getByIds(listIdRooms));
        }

        if (StringUtils.isNotBlank(item.getIdCustomer())) {
            registrationResponse.setEmployee(qlksEmployeeRepository.getById(item.getIdEmployee()).get());
        }

        return registrationResponse;
    }
}
