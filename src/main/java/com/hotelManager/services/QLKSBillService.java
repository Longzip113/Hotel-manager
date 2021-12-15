package com.hotelManager.services;

import com.hotelManager.dtos.request.TurnOverRequest;
import com.hotelManager.dtos.responses.QLKSInfoBillResponse;
import com.hotelManager.dtos.responses.QLKSInfoTurnOverResponse;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;


public interface QLKSBillService {

    List<QLKSInfoBillResponse> getAll() throws HotelManagerException;

    List<QLKSInfoTurnOverResponse> getTurnOver(TurnOverRequest turnOverRequest) throws HotelManagerException;
}
