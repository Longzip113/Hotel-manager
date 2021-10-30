package com.hotelManager.repositories;

import com.hotelManager.dtos.request.UpdateCustomerRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSCustomerRepository {
    void save(QLKSCustomerEntity qlksCustomerEntity) throws HotelManagerException;

    void delete(String idCustom);

    void update(UpdateCustomerRequest customerRequest, String idCustom) throws HotelManagerException;

    Optional<QLKSCustomerEntity> getByPhoneNumber(String phone, String id) throws HotelManagerException;

    Optional<QLKSCustomerEntity> getByEmail(String email, String id) throws HotelManagerException;

    Optional<QLKSCustomerEntity> getByCard(String card, String id) throws HotelManagerException;

    List<QLKSCustomerEntity> getAll(String sortBy, String sortOrder);

    Optional<QLKSCustomerEntity> getById(String idCustom) throws HotelManagerException;
}
