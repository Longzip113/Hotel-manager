package com.hotelManager.services;

import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSTypeRoomService {
    List<QLKSTypeRoomEntity> getAll() throws HotelManagerException;
}
