package com.hotelManager.services;

import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.exceptions.HotelManagerException;

public interface QLKSRoomService {

    void save (AddRoomRequest addRoomRequest) throws HotelManagerException;
}
