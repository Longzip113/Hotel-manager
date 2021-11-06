package com.hotelManager.repositories;

import com.hotelManager.dtos.request.ChangePasswordRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.dtos.request.UpdateUserRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSDetailTypeRoomEntity;
import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface QLKSEmployeeRepository {

    List<QLKSEmployeeModel> getAll() throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateUserRequest userRequest) throws HotelManagerException;

    String save (QLKSEmployeeEntity qlksEmployeeEntity, Session session) throws HotelManagerException;

    Optional<QLKSEmployeeModel> getById(String id);

    Optional<QLKSEmployeeModel> getEmployeeByEmailAndPassWord(String passWord, String email);

    Optional<QLKSEmployeeEntity> getByEmailDefId(String email, String id) throws HotelManagerException;

    Optional<QLKSEmployeeEntity> getByEmail(String email) throws HotelManagerException;

    Optional<QLKSEmployeeEntity> getByCardDefId(String card, String id) throws HotelManagerException;

    void saveVerification(String email, String veri, String id) throws HotelManagerException;

    void resetPasswordByVerification(ChangePasswordRequest changePasswordRequest) throws HotelManagerException;
}
