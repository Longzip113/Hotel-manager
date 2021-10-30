package com.hotelManager.services;

import com.hotelManager.dtos.request.AddTypeRoomRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSTypeRoomService {
    List<QLKSTypeRoomEntity> getAll() throws HotelManagerException;

    void addTypeRoom(AddTypeRoomRequest addTypeRoomRequest) throws HotelManagerException;

    void deleteTypeRoom(String id) throws HotelManagerException;

    void updateTypeRoom(String id, UpdateTypeRoomRequest updateTypeRoomRequest) throws HotelManagerException;

    QLKSTypeRoomEntity getDetailTypeRoom(String id) throws HotelManagerException;
}
