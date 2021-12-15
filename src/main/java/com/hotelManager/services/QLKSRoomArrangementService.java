package com.hotelManager.services;

import com.hotelManager.dtos.responses.QLKSInfoCheckOutResponse;
import com.hotelManager.exceptions.HotelManagerException;


public interface QLKSRoomArrangementService {

    void checkIn(String id) throws HotelManagerException;

    void checkOut(String id) throws HotelManagerException;

    void cancel(String id) throws HotelManagerException;

    QLKSInfoCheckOutResponse getInfoCheckOut(String id) throws HotelManagerException;

}
