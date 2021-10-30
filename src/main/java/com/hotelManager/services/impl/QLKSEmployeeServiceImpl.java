package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.AddUserRequest;
import com.hotelManager.dtos.request.UserRequest;
import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.entities.QLKSUserEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.exceptions.ValidateException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.repositories.QLKSEmployeeRepository;
import com.hotelManager.repositories.QLKSUserRepository;
import com.hotelManager.services.QLKSEmployeeService;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hotelManager.constants.Constants.ID_ROLE_USER;
import static com.hotelManager.constants.Constants.PASSWORD_DEFAULT;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_PASSWORD_EMAIL;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Service
@Slf4j
public class QLKSEmployeeServiceImpl implements QLKSEmployeeService {

    @Autowired
    QLKSUserRepository qlksUserRepository;

    @Autowired
    QLKSEmployeeRepository qlksEmployeeRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(AddUserRequest addUserRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            // Create transaction
            HibernateUtils.beginTransaction(session);
            QLKSEmployeeEntity qlksEmployeeEntity = QLKSEmployeeEntity.builder()
                    .address(addUserRequest.getAddress())
                    .gender(addUserRequest.getGender())
                    .nameEmployee(addUserRequest.getNameEmployee())
                    .isDeleted(Boolean.FALSE)
                    .identityCard(addUserRequest.getIdentityCard())
                    .phoneNumber(addUserRequest.getPhoneNumber())
                    .build();

            String idEmployee = qlksEmployeeRepository.save(qlksEmployeeEntity, session);

            QLKSUserEntity qlksUserEntity = QLKSUserEntity.builder()
                    .email(addUserRequest.getEmail())
                    .passWord(PASSWORD_DEFAULT)
                    .id(idEmployee)
                    .isDelete(Boolean.FALSE)
                    .idRole(ID_ROLE_USER).build();

            qlksUserRepository.save(qlksUserEntity, session);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Save QLKSUserEntity failed", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }

    }

    @Override
    public QLKSEmployeeModel login(UserRequest userRequest) throws HotelManagerException {
        Optional<QLKSEmployeeModel> qlksEmployeeModel = qlksEmployeeRepository.getEmployeeByEmailAndPassWord(userRequest.getPassWord(), userRequest.getEmail());

        if (qlksEmployeeModel.isEmpty()) {
            log.error("Email address or password is incorrect.");
            HotelManagerUtils.throwException(ValidateException.class, ERROR_PASSWORD_EMAIL);
        }
        return qlksEmployeeModel.get();
    }
}
