package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSBillEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;


public interface QLKSBillRepository {

    void save(QLKSBillEntity entity) throws HotelManagerException;

    List<QLKSBillEntity> getAll() throws HotelManagerException;

    List<QLKSBillEntity> getAllByDay(Long start, Long end) throws HotelManagerException;

    Optional<QLKSBillEntity> getOne(String id) throws HotelManagerException;

    List<QLKSBillEntity> getByRegistrationAndRoom(String idRegistration, String idRoom) throws HotelManagerException;
}
