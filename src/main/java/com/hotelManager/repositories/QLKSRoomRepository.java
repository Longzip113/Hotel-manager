package com.hotelManager.repositories;

import com.hotelManager.dtos.request.RoomRequest;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;

import java.util.List;
import java.util.Optional;

public interface QLKSRoomRepository {
    String save(QLKSRoomEntity qlksRoomEntity) throws HotelManagerException;

    void delete(String idRoom);

    void update(RoomRequest roomRequest, String idRoom) throws HotelManagerException;

    void updateBatch(List<QLKSRoomEntity> listEntity) throws HotelManagerException;

    void update(QLKSRoomEntity entity) throws HotelManagerException;

    Optional<QLKSRoomEntity> getByNameRoom(String nameRoom) throws HotelManagerException;

    List<QLKSRoomModel> getAll(String sortBy, String sortOrder);

    List<QLKSRoomEntity> getAll() throws HotelManagerException;

    Optional<QLKSRoomModel> getByIdRoom(String idRoom);

    Optional<QLKSRoomEntity> getByIdRoomEntity(String idRoom) throws HotelManagerException;

    List<QLKSRoomModel> getByIds(List<String> id);

    List<QLKSRoomModel> getByNotIds(List<String> id);

    long priceRooms(List<String> idRooms);

    void updateStatus(List<String> idRoom, Integer statusRoom, Integer statusClear) throws HotelManagerException;

    void updateStatusClear(List<String> idRoom, Integer statusClear) throws HotelManagerException;
}
