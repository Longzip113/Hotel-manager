package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSLogCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;


public interface QLKSLogCustomerRepository {

    void save(QLKSLogCustomerEntity entity) throws HotelManagerException;

    List<QLKSLogCustomerEntity> getAll() throws HotelManagerException;

    Optional<QLKSLogCustomerEntity> getOne(String id) throws HotelManagerException;

    List<QLKSLogCustomerEntity> getByRegistrationAndRoom(String idRegistration, String idRoom) throws HotelManagerException;
}
