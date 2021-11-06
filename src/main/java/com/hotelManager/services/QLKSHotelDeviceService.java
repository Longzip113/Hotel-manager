package com.hotelManager.services;

import com.hotelManager.dtos.request.AddHotelDeviceRequest;
import com.hotelManager.dtos.request.AddTypeRoomRequest;
import com.hotelManager.dtos.request.UpdateHotelDeviceRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSHotelDeviceService {

    List<QLKSHotelDeviceEntity> getAll() throws HotelManagerException;

    void add(AddHotelDeviceRequest addHotelDeviceRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateHotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException;

    QLKSHotelDeviceEntity getDetail(String id) throws HotelManagerException;

}
