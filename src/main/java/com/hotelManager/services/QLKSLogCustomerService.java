package com.hotelManager.services;

import com.hotelManager.dtos.request.LogCustomerRequest;
import com.hotelManager.dtos.responses.QLKSLogCustomerResponse;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSLogCustomerService {

    void add(LogCustomerRequest request) throws HotelManagerException;

    List<QLKSLogCustomerResponse> getAll() throws HotelManagerException;

    QLKSLogCustomerResponse getDetail(String id) throws HotelManagerException;
}
