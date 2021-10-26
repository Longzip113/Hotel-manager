package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

public interface QLKSRoomRepository {
    void save(QLKSRoomEntity qlksRoomEntity) throws HotelManagerException;
}
