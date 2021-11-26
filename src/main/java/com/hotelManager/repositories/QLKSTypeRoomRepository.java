package com.hotelManager.repositories;

import com.hotelManager.dtos.request.TypeRoomRequest;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSTypeRoomRepository {
    List<QLKSTypeRoomEntity> getAll() throws HotelManagerException;

    String save(QLKSTypeRoomEntity qlksTypeRoomEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, TypeRoomRequest typeRoomRequest) throws HotelManagerException;

    Optional<QLKSTypeRoomEntity> getById(String id) throws HotelManagerException;

    Optional<QLKSTypeRoomEntity> getByNameType(String nameType) throws HotelManagerException;

    Optional<QLKSTypeRoomEntity> getByNameTypeAndPrice(String nameType, Integer price, String description) throws HotelManagerException;

    long countRoomByTypeRoom(String idTypeRoom);
}
