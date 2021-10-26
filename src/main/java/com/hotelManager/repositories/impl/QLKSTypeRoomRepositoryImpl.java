package com.hotelManager.repositories.impl;

import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSTypeRoomRepositoryImpl implements QLKSTypeRoomRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSTypeRoomEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSTypeRoomEntity");

            log.info("SQL [{}]", hql);

            Query<QLKSTypeRoomEntity> query = session.createQuery(hql.toString(), QLKSTypeRoomEntity.class);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("Get one NodeTag by nodeId failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
