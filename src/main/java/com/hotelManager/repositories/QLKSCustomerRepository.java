package com.hotelManager.repositories;

import com.hotelManager.dtos.request.CustomerRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSCustomerRepository {
    void save(QLKSCustomerEntity qlksCustomerEntity) throws HotelManagerException;

    void delete(String idCustom);

    void update(CustomerRequest customerRequest, String idCustom) throws HotelManagerException;

    Optional<QLKSCustomerEntity> getByPhoneNumber(String phone, String id) throws HotelManagerException;

    Optional<QLKSCustomerEntity> getByEmail(String email, String id) throws HotelManagerException;

    Optional<QLKSCustomerEntity> getByCard(String card, String id) throws HotelManagerException;

    List<QLKSCustomerEntity> getAll(String sortBy, String sortOrder);

    List<QLKSCustomerEntity> getByIds(List<String> ids);

    Optional<QLKSCustomerEntity> getById(String idCustom) throws HotelManagerException;
}
