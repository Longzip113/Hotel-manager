package com.hotelManager.repositories;

import com.hotelManager.dtos.request.UpdateHotelDeviceRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSHotelDeviceRepository {
    List<QLKSHotelDeviceEntity> getAll() throws HotelManagerException;

    void save(QLKSHotelDeviceEntity qlksHotelDeviceEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateHotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException;

    Optional<QLKSHotelDeviceEntity> getById(String id) throws HotelManagerException;

    Optional<QLKSHotelDeviceEntity> getByName(String name) throws HotelManagerException;

    Optional<QLKSHotelDeviceEntity> getByNameAndPrice(String name, Integer price) throws HotelManagerException;

    long countRoomByHotelDevice(String idHotelDevice);
}
