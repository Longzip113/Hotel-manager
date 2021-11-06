package com.hotelManager.repositories;

import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.entities.QLKSDetailTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSDetailTypeRoomRepository {
    List<QLKSDetailTypeRoomEntity> getByTypeRoom() throws HotelManagerException;

    void save(QLKSDetailTypeRoomEntity qlksDetailTypeRoomEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateTypeRoomRequest updateTypeRoomRequest) throws HotelManagerException;

    Optional<QLKSDetailTypeRoomEntity> getById(String id) throws HotelManagerException;
}
