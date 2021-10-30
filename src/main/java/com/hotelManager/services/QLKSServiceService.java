package com.hotelManager.services;

import com.hotelManager.dtos.request.AddServiceRequest;
import com.hotelManager.dtos.request.UpdateServiceRequest;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSServiceService {
    List<QLKSServiceEntity> getAll() throws HotelManagerException;

    void addService(AddServiceRequest addServiceRequest) throws HotelManagerException;

    void deleteService(String id) throws HotelManagerException;

    void updateService(String id, UpdateServiceRequest updateServiceRequest) throws HotelManagerException;

    QLKSServiceEntity getDetailService(String id) throws HotelManagerException;
}
