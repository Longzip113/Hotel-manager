package com.hotelManager.services;

import com.hotelManager.exceptions.HotelManagerException;

public interface QLKSClearScheduleService {

    void save(Long dayWork) throws HotelManagerException;
}
