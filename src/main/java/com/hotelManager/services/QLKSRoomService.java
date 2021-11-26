package com.hotelManager.services;

import com.hotelManager.dtos.request.RoomRequest;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;

import java.util.List;

public interface QLKSRoomService {

    QLKSRoomModel save(RoomRequest addRoomRequest) throws HotelManagerException;

    void deleteRoom(String idRoom) throws HotelManagerException;

    void update(RoomRequest roomRequest, String idRoom) throws HotelManagerException;

    List<QLKSRoomModel> getAll(String sortBy, String sortOrder);

    QLKSRoomModel getDetailRoom(String idRoom) throws HotelManagerException;
}
