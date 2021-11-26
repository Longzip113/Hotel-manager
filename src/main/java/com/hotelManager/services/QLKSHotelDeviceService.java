package com.hotelManager.services;

import com.hotelManager.dtos.request.HotelDeviceRequest;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSHotelDeviceService {

    List<QLKSHotelDeviceEntity> getAll() throws HotelManagerException;

    QLKSHotelDeviceEntity add(HotelDeviceRequest hotelDeviceRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, HotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException;

    QLKSHotelDeviceEntity getDetail(String id) throws HotelManagerException;

}
