package com.hotelManager.repositories.impl;

import com.hotelManager.entities.QLKSLogCustomerEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSLogCustomerRepository;
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
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSLogCustomerRepositoryImpl implements QLKSLogCustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(QLKSLogCustomerEntity entity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(entity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSLogCustomerEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(entity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSLogCustomerEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSLogCustomerEntity ");

            log.info("SQL [{}]", hql);

            Query<QLKSLogCustomerEntity> query = session.createQuery(hql.toString(), QLKSLogCustomerEntity.class);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getAll QLKSLogCustomerEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSLogCustomerEntity> getOne(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSLogCustomerEntity r ")
                    .append("WHERE r.id = :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSLogCustomerEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSLogCustomerEntity> getByRegistrationAndRoom(String idRegistration, String idRoom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSLogCustomerEntity l ")
                    .append("WHERE l.idRegistrationForm = :idRegistrationForm AND l.idRoom = :idRoom ");

            log.info("SQL [{}]", hql);

            Query<QLKSLogCustomerEntity> query = session.createQuery(hql.toString(), QLKSLogCustomerEntity.class)
                    .setParameter("idRegistrationForm", idRegistration)
                    .setParameter("idRoom", idRoom);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getByRegistration QLKSLogCustomerEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
