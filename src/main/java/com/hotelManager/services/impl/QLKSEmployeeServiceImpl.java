package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.*;
import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.exceptions.ValidateException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.repositories.QLKSEmployeeRepository;
import com.hotelManager.services.ClientService;
import com.hotelManager.services.QLKSEmployeeService;
import com.hotelManager.utils.ConvertPassword;
import com.hotelManager.utils.DataUtils;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.hotelManager.constants.Constants.ID_ROLE_USER;
import static com.hotelManager.constants.Constants.PASSWORD_DEFAULT;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSEmployeeServiceImpl implements QLKSEmployeeService {

    @Autowired
    QLKSEmployeeRepository qlksEmployeeRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    ClientService clientService;

    @Override
    public QLKSEmployeeModel save(UserRequest addUserRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            // Create transaction
            HibernateUtils.beginTransaction(session);
            QLKSEmployeeEntity qlksEmployeeEntity = QLKSEmployeeEntity.builder()
                    .address(addUserRequest.getAddress())
                    .gender(addUserRequest.getGender())
                    .nameEmployee(addUserRequest.getNameEmployee())
                    .isDelete(Boolean.FALSE)
                    .identityCard(addUserRequest.getIdentityCard())
                    .phoneNumber(addUserRequest.getPhoneNumber())
                    .email(addUserRequest.getEmail())
                    .idRole(addUserRequest.getIdRole())
                    .passWord(PASSWORD_DEFAULT)
                    .build();

            String id = qlksEmployeeRepository.save(qlksEmployeeEntity, session);
            session.getTransaction().commit();
            return getDetail(id);
        } catch (Exception e) {
            log.error("Save QLKSEmployeeEntity failed", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    @Override
    public QLKSEmployeeModel login(LoginRequest userRequest) throws HotelManagerException, NoSuchAlgorithmException {
        Optional<QLKSEmployeeModel> qlksEmployeeModel = qlksEmployeeRepository.getEmployeeByEmailAndPassWord(ConvertPassword.getMD5(userRequest.getPassWord()), userRequest.getEmail());

        if (qlksEmployeeModel.isEmpty()) {
            log.error("Email address or password is incorrect.");
            HotelManagerUtils.throwException(ValidateException.class, ERROR_PASSWORD_EMAIL);
        }
        return qlksEmployeeModel.get();
    }

    @Override
    public List<QLKSEmployeeModel> getAll() throws HotelManagerException {
        return qlksEmployeeRepository.getAll();
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Optional<QLKSEmployeeModel> qlksEmployeeModel = qlksEmployeeRepository.getById(id);
        if (qlksEmployeeModel.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        qlksEmployeeRepository.delete(id);
    }

    @Override
    public QLKSEmployeeModel getDetail(String id) throws HotelManagerException {
        Optional<QLKSEmployeeModel> qlksEmployeeModel = qlksEmployeeRepository.getById(id);
        if (qlksEmployeeModel.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        return qlksEmployeeModel.get();
    }

    @Override
    public void update(UserRequest userRequest, String id) throws HotelManagerException {
        if (qlksEmployeeRepository.getById(id).isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (qlksEmployeeRepository.getByEmailDefId(userRequest.getEmail(), id).isPresent()) {
            log.error("Email room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_EMAIL_ALREADY_EXISTED);
        }

        if (qlksEmployeeRepository.getByCardDefId(userRequest.getIdentityCard(), id).isPresent()) {
            log.error("IdentityCard existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_CARD_ALREADY_EXISTED);
        }

        qlksEmployeeRepository.update(id, userRequest);
    }

    @Override
    public void getVerification(GetVerificationRequest getPasswordRequest) throws HotelManagerException {
        Optional<QLKSEmployeeEntity> qlksEmployeeEntity = qlksEmployeeRepository.getByEmail(getPasswordRequest.getEmail());

        if (qlksEmployeeEntity.isEmpty()) {
            log.error("Email existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_EMAIL_NOT_EXISTED);
        }
        CompletableFuture.runAsync(() -> {
            String verification = DataUtils.generateTempPwd(5);
            try {
                qlksEmployeeRepository.saveVerification(qlksEmployeeEntity.get().getEmail(),
                        verification,qlksEmployeeEntity.get().getId());
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }

            clientService.create(qlksEmployeeEntity.get().getEmail(), verification);
        });
    }

    @Override
    public void getPassword(GetPasswordRequest changePasswordRequest) throws HotelManagerException {
        qlksEmployeeRepository.resetPasswordByVerification(changePasswordRequest);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) throws HotelManagerException {
        qlksEmployeeRepository.changePasswordByEmail(changePasswordRequest);
    }
}
