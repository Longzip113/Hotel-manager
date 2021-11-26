package com.hotelManager.services;

import com.hotelManager.dtos.request.LogCustomerRequest;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSLogCustomerService {

    void add(LogCustomerRequest request) throws HotelManagerException;

}
