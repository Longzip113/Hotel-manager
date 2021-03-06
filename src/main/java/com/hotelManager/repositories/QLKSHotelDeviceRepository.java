package com.hotelManager.repositories;

import com.hotelManager.dtos.request.HotelDeviceRequest;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSHotelDeviceRepository {
    List<QLKSHotelDeviceEntity> getAll() throws HotelManagerException;

    String save(QLKSHotelDeviceEntity qlksHotelDeviceEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, HotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException;

    void update(QLKSHotelDeviceEntity entity) throws HotelManagerException;

    Optional<QLKSHotelDeviceEntity> getById(String id) throws HotelManagerException;

    Optional<QLKSHotelDeviceEntity> getByName(String name) throws HotelManagerException;

    Optional<QLKSHotelDeviceEntity> getByNameAndPrice(String name, Integer price) throws HotelManagerException;

    long countRoomByHotelDevice(String idHotelDevice);
}
