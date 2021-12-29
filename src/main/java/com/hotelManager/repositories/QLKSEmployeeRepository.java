package com.hotelManager.repositories;

import com.hotelManager.dtos.request.ChangePasswordRequest;
import com.hotelManager.dtos.request.GetPasswordRequest;
import com.hotelManager.dtos.request.UserRequest;
import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface QLKSEmployeeRepository {

    List<QLKSEmployeeModel> getAll() throws HotelManagerException;

    List<QLKSEmployeeModel> getEmployeeClear() throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UserRequest userRequest) throws HotelManagerException;

    String save (QLKSEmployeeEntity qlksEmployeeEntity, Session session) throws HotelManagerException;

    Optional<QLKSEmployeeModel> getById(String id);

    Optional<QLKSEmployeeModel> getEmployeeByEmailAndPassWord(String passWord, String email);

    Optional<QLKSEmployeeEntity> getByEmailDefId(String email, String id) throws HotelManagerException;

    Optional<QLKSEmployeeEntity> getByEmail(String email) throws HotelManagerException;

    Optional<QLKSEmployeeEntity> getByCardDefId(String card, String id) throws HotelManagerException;

    void saveVerification(String email, String veri, String id) throws HotelManagerException;

    void resetPasswordByVerification(GetPasswordRequest getPasswordRequest) throws HotelManagerException;

    void changePasswordByEmail(ChangePasswordRequest changePasswordRequest) throws HotelManagerException;
}
