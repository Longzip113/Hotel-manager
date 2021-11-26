package com.hotelManager.services;

import com.hotelManager.exceptions.HotelManagerException;


public interface QLKSRoomArrangementService {

    void checkIn(String id) throws HotelManagerException;

    void checkOut(String id) throws HotelManagerException;

}
