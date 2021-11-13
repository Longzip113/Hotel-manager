package com.hotelManager.repositories;

import com.hotelManager.dtos.request.RoomRequest;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;

import java.util.List;
import java.util.Optional;

public interface QLKSRoomRepository {
    void save(QLKSRoomEntity qlksRoomEntity) throws HotelManagerException;

    void delete(String idRoom);

    void update(RoomRequest roomRequest, String idRoom) throws HotelManagerException;

    Optional<QLKSRoomEntity> getByNameRoom(String nameRoom) throws HotelManagerException;

    List<QLKSRoomModel> getAll(String sortBy, String sortOrder);

    Optional<QLKSRoomModel> getByIdRoom(String idRoom);
}
