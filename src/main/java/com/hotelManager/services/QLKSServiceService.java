package com.hotelManager.services;

import com.hotelManager.dtos.request.ServiceRequest;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSServiceService {
    List<QLKSServiceEntity> getAll() throws HotelManagerException;

    void addService(ServiceRequest addServiceRequest) throws HotelManagerException;

    void deleteService(String id) throws HotelManagerException;

    void updateService(String id, ServiceRequest updateServiceRequest) throws HotelManagerException;

    QLKSServiceEntity getDetailService(String id) throws HotelManagerException;
}
