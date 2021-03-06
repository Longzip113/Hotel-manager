package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.CustomerRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSCustomerRepository;
import com.hotelManager.services.QLKSCustomerService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSCustomerServiceImpl implements QLKSCustomerService {

    @Autowired
    QLKSCustomerRepository qlksCustomerRepository;



    @Override
    public void save(CustomerRequest customerRequest) throws HotelManagerException {
        if (qlksCustomerRepository.getByEmail(customerRequest.getNameCustomer(), "").isPresent()) {
            log.error("Email room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_EMAIL_ALREADY_EXISTED);
        }

        if (qlksCustomerRepository.getByPhoneNumber(customerRequest.getPhoneNumber(), "").isPresent()) {
            log.error("Phone existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_PHONE_ALREADY_EXISTED);
        }

        if (qlksCustomerRepository.getByCard(customerRequest.getIdentityCard(), "").isPresent()) {
            log.error("IdentityCard existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_CARD_ALREADY_EXISTED);
        }

        QLKSCustomerEntity qlksCustomerEntity = QLKSCustomerEntity.builder()
                .card(customerRequest.getIdentityCard())
                .email(customerRequest.getEmail())
                .name(customerRequest.getNameCustomer())
                .nationality(customerRequest.getNationality())
                .phone(customerRequest.getPhoneNumber())
                .isDelete(Boolean.FALSE).build();

        qlksCustomerRepository.save(qlksCustomerEntity);
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        if (qlksCustomerRepository.getById(id).isEmpty()) {
            log.error("idCustomer not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        qlksCustomerRepository.delete(id);
    }

    @Override
    public void update(CustomerRequest customerRequest, String id) throws HotelManagerException {

        if (qlksCustomerRepository.getById(id).isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (qlksCustomerRepository.getByEmail(customerRequest.getNameCustomer(), id).isPresent()) {
            log.error("Email room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_EMAIL_ALREADY_EXISTED);
        }

        if (qlksCustomerRepository.getByPhoneNumber(customerRequest.getPhoneNumber(), id).isPresent()) {
            log.error("Phone existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_PHONE_ALREADY_EXISTED);
        }

        if (qlksCustomerRepository.getByCard(customerRequest.getIdentityCard(), id).isPresent()) {
            log.error("IdentityCard existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_CARD_ALREADY_EXISTED);
        }

        qlksCustomerRepository.update(customerRequest, id);
    }

    @Override
    public List<QLKSCustomerEntity> getAll(String sortBy, String sortOrder) {
        return qlksCustomerRepository.getAll(sortBy, sortOrder);
    }

    @Override
    public QLKSCustomerEntity getDetail(String id) throws HotelManagerException {
        if (!StringUtils.isNotBlank(id)) {
            return null;
        }

        if(qlksCustomerRepository.getById(id).isEmpty()) {
            log.error("Id does not exist ! {}", id);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        return qlksCustomerRepository.getById(id).get();
    }
}
