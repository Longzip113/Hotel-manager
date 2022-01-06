package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;


public interface QLKSRegistrationFormRepository {

    List<QLKSRegistrationFormEntity> getAll() throws HotelManagerException;

    List<QLKSRegistrationFormEntity> getByIdDelegation(String idDelegation) throws HotelManagerException;

    List<QLKSRegistrationFormEntity> getByCheckIn() throws HotelManagerException;

    void updateByIdDelegation(String idDelegation, String idCustomers, Integer size) throws HotelManagerException;

    void save(QLKSRegistrationFormEntity entity) throws HotelManagerException;

    void update(QLKSRegistrationFormEntity entity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    Optional<QLKSRegistrationFormEntity> getById(String id) throws HotelManagerException;

    Optional<QLKSRegistrationFormEntity> getByIdRoomAndTime(String idRoom, Long time) throws HotelManagerException;

    Optional<QLKSRegistrationFormEntity> getByIdRoomAndTime(String idRoom, Long timeStart, Long timeEnd) throws HotelManagerException;

    Optional<QLKSRegistrationFormEntity> checkRoom(String idRoom, Long timeStart, Long timeEnd) throws HotelManagerException;

    Optional<QLKSRegistrationFormEntity> checkCustomer(String idCustomer, Long timeStart, Long timeEnd) throws HotelManagerException;

    void updateChangeRoom(String idRegistration, String idRoomOld, String idRoomNew) throws HotelManagerException;
}
