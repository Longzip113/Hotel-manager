package com.hotelManager.services;

import com.hotelManager.dtos.request.AddUserRequest;
import com.hotelManager.dtos.request.UserRequest;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;

public interface QLKSEmployeeService {

    void save(AddUserRequest addUserRequest) throws HotelManagerException;

    QLKSEmployeeModel login(UserRequest userRequest) throws HotelManagerException;

}
