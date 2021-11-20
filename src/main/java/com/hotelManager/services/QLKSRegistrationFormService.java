package com.hotelManager.services;

import com.hotelManager.dtos.request.RegistrationRequest;
import com.hotelManager.dtos.responses.QLKSRegistrationResponse;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSRegistrationFormService {

    List<QLKSRegistrationResponse> getAll() throws HotelManagerException;

    void add(RegistrationRequest request) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, RegistrationRequest request) throws HotelManagerException;

    QLKSRegistrationResponse getDetail(String id) throws HotelManagerException;

}
