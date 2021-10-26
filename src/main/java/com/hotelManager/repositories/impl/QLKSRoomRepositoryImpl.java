package com.hotelManager.repositories.impl;

import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRoomRepository;
import com.hotelManager.utils.GsonHelper;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSRoomRepositoryImpl implements QLKSRoomRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(QLKSRoomEntity qlksRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksRoomEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSRoomEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksRoomEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
