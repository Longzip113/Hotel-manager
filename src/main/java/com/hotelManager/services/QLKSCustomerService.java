package com.hotelManager.services;

import com.hotelManager.dtos.request.AddCustomerRequest;
import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.dtos.request.UpdateCustomerRequest;
import com.hotelManager.dtos.request.UpdateRoomRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;

import java.util.List;

public interface QLKSCustomerService {

    void save(AddCustomerRequest addCustomerRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(UpdateCustomerRequest customerRequest, String id) throws HotelManagerException;

    List<QLKSCustomerEntity> getAll(String sortBy, String sortOrder);

    QLKSCustomerEntity getDetail(String id) throws HotelManagerException;

}
