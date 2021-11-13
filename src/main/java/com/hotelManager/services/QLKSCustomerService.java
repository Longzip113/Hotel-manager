package com.hotelManager.services;

import com.hotelManager.dtos.request.CustomerRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSCustomerService {

    void save(CustomerRequest customerRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(CustomerRequest customerRequest, String id) throws HotelManagerException;

    List<QLKSCustomerEntity> getAll(String sortBy, String sortOrder);

    QLKSCustomerEntity getDetail(String id) throws HotelManagerException;

}
