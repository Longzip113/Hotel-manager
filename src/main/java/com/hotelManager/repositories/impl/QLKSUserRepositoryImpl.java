package com.hotelManager.repositories.impl;

import com.hotelManager.entities.QLKSUserEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSUserRepository;
import com.hotelManager.utils.GsonHelper;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSUserRepositoryImpl implements QLKSUserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(QLKSUserEntity qlksUserEntity, Session session) throws HotelManagerException {
        try {
            session.save(qlksUserEntity);
        } catch (PersistenceException e) {

            log.error("Save QLKSUserEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksUserEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        }
    }
}