package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSTypeRoomRepository {
    List<QLKSTypeRoomEntity> getAll() throws HotelManagerException;

    void save(QLKSTypeRoomEntity qlksTypeRoomEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    long countRoomByTypeRoom(String idTypeRoom);
}
