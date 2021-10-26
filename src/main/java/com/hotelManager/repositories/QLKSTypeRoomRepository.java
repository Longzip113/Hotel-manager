package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSTypeRoomRepository {
    List<QLKSTypeRoomEntity> getAll() throws HotelManagerException;
}
