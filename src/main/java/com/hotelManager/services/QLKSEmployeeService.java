package com.hotelManager.services;

import com.hotelManager.dtos.request.*;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;

import java.util.List;

public interface QLKSEmployeeService {

    void save(AddUserRequest addUserRequest) throws HotelManagerException;

    QLKSEmployeeModel login(UserRequest userRequest) throws HotelManagerException;

    List<QLKSEmployeeModel> getAll() throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    QLKSEmployeeModel getDetail(String id) throws HotelManagerException;

    void update(UpdateUserRequest userRequest, String id) throws HotelManagerException;

    void getVerification(GetVerificationRequest getPasswordRequest) throws HotelManagerException;

    void changDefaultPassword(ChangePasswordRequest changePasswordRequest) throws HotelManagerException;

}
