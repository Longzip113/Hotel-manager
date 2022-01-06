package com.hotelManager.services;

import com.hotelManager.dtos.request.*;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface QLKSEmployeeService {

    QLKSEmployeeModel save(UserRequest addUserRequest) throws HotelManagerException;

    QLKSEmployeeModel login(LoginRequest userRequest) throws HotelManagerException, NoSuchAlgorithmException;

    List<QLKSEmployeeModel> getAll() throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    QLKSEmployeeModel getDetail(String id) throws HotelManagerException;

    void update(UserRequest userRequest, String id) throws HotelManagerException;

    void getVerification(GetVerificationRequest getPasswordRequest) throws HotelManagerException;

    void getPassword(GetPasswordRequest getPasswordRequest) throws HotelManagerException;

    void changePassword(ChangePasswordRequest changePasswordRequest) throws HotelManagerException;

}
