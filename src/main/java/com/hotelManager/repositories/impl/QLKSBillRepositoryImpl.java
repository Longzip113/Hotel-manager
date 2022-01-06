package com.hotelManager.repositories.impl;

import com.hotelManager.entities.QLKSBillEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSBillRepository;
import com.hotelManager.utils.GsonHelper;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class QLKSBillRepositoryImpl implements QLKSBillRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(QLKSBillEntity entity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(entity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSBillEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(entity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSBillEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSBillEntity WHERE isDelete = FALSE ");

            log.info("SQL [{}]", hql);

            Query<QLKSBillEntity> query = session.createQuery(hql.toString(), QLKSBillEntity.class);

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
    public List<QLKSBillEntity> getAllByDay(Long start, Long end) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSBillEntity WHERE isDelete = FALSE AND dayOfPayment BETWEEN :start AND :end ");

            log.info("SQL [{}]", hql);

            Query<QLKSBillEntity> query = session.createQuery(hql.toString(), QLKSBillEntity.class)
                    .setParameter("start", start)
                    .setParameter("end", end);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getAllByDay QLKSLogCustomerEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSBillEntity> getOne(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSBillEntity l ")
                    .append("WHERE l.idRegistrationForm = :idRegistrationForm ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("idRegistrationForm", id);

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
    public List<QLKSBillEntity> getByRegistrationAndRoom(String idRegistration, String idRoom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSBillEntity l ")
                    .append("WHERE l.idRegistrationForm = :idRegistrationForm AND l.idRoom like :idRoom ");

            log.info("SQL [{}]", hql);

            Query<QLKSBillEntity> query = session.createQuery(hql.toString(), QLKSBillEntity.class)
                    .setParameter("idRegistrationForm", idRegistration)
                    .setParameter("idRoom", "%" + idRoom + "%");

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getByRegistration QLKSBillEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
