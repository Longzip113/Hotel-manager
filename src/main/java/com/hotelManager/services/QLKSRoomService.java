package com.hotelManager.services;

import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.dtos.request.UpdateRoomRequest;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;

import java.util.List;

public interface QLKSRoomService {

    void save(AddRoomRequest addRoomRequest) throws HotelManagerException;

    void deleteRoom(String idRoom) throws HotelManagerException;

    void update(UpdateRoomRequest roomRequest, String idRoom) throws HotelManagerException;

    List<QLKSRoomModel> getAll(String sortBy, String sortOrder);

    QLKSRoomModel getDetailRoom(String idRoom) throws HotelManagerException;
}
