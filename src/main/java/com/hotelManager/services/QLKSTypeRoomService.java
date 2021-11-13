package com.hotelManager.services;

import com.hotelManager.dtos.request.TypeRoomRequest;
import com.hotelManager.dtos.responses.QLKSTypeRoomReponse;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSTypeRoomService {
    List<QLKSTypeRoomReponse> getAll() throws HotelManagerException;

    void addTypeRoom(TypeRoomRequest typeRoomRequest) throws HotelManagerException;

    void deleteTypeRoom(String id) throws HotelManagerException;

    void updateTypeRoom(String id, TypeRoomRequest typeRoomRequest) throws HotelManagerException;

    QLKSTypeRoomReponse getDetailTypeRoom(String id) throws HotelManagerException;
}
