package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;


public interface QLKSRegistrationFormRepository {

    List<QLKSRegistrationFormEntity> getAll() throws HotelManagerException;

    void save(QLKSRegistrationFormEntity entity) throws HotelManagerException;

    void update(QLKSRegistrationFormEntity entity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    Optional<QLKSRegistrationFormEntity> getById(String id) throws HotelManagerException;
}
