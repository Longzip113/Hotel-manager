package com.hotelManager.services;

import com.hotelManager.dtos.request.DelegationRequest;
import com.hotelManager.dtos.responses.QLKSDelegationResponse;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSDelegationService {

    List<QLKSDelegationResponse> getAll() throws HotelManagerException;

    void add(DelegationRequest addDelegationRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, DelegationRequest updateDelegationRequest) throws HotelManagerException;

    QLKSDelegationResponse getDetail(String id) throws HotelManagerException;

}
