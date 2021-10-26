package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import org.hibernate.Session;

import java.util.Optional;

public interface QLKSEmployeeRepository {

    String save (QLKSEmployeeEntity qlksEmployeeEntity, Session session) throws HotelManagerException;

    Optional<QLKSEmployeeModel> getEmployeeByEmailAndPassWord(String passWord, String email);
}
