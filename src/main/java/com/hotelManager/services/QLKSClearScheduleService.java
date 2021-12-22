package com.hotelManager.services;

import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.dtos.request.GetViewCleanScheduleRequest;
import com.hotelManager.dtos.responses.QLKSCleanScheduleResponse;
import com.hotelManager.dtos.responses.QLKSLogCleanScheduleResponse;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSClearScheduleService {

    void save() throws HotelManagerException;

    List<QLKSCleanScheduleResponse> getAll(CleanScheduleRequest request) throws HotelManagerException;

    List<QLKSLogCleanScheduleResponse> getAllLog(CleanScheduleRequest request) throws HotelManagerException;

    List<QLKSCleanScheduleResponse> getAllViewRoom(GetViewCleanScheduleRequest request) throws HotelManagerException;

    void checkInClean(String idRoom, String idEmployee) throws HotelManagerException;

    void checkOutClean(String idRoom, String idEmployee) throws HotelManagerException;
}
