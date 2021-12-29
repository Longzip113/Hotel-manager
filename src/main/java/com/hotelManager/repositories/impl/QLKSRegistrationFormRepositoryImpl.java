package com.hotelManager.repositories.impl;

import com.hotelManager.constants.enums.BookingType;
import com.hotelManager.constants.enums.TypeRegister;
import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRegistrationFormRepository;
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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSRegistrationFormRepositoryImpl implements QLKSRegistrationFormRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSRegistrationFormEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSRegistrationFormEntity WHERE isDelete = :idDelete ");

            log.info("SQL [{}]", hql);

            Query<QLKSRegistrationFormEntity> query = session.createQuery(hql.toString(), QLKSRegistrationFormEntity.class)
                    .setParameter("idDelete", Boolean.FALSE);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getAll QLKSRoleEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSRegistrationFormEntity> getByIdDelegation(String idDelegation) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSRegistrationFormEntity WHERE isDelete = :idDelete AND idDelegation = :idDelegation ");

            log.info("SQL [{}]", hql);

            Query<QLKSRegistrationFormEntity> query = session.createQuery(hql.toString(), QLKSRegistrationFormEntity.class)
                    .setParameter("idDelete", Boolean.FALSE)
                    .setParameter("idDelegation", idDelegation);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getByIdDelegation QLKSRoleEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void updateByIdDelegation(String idDelegation, String idCustomers, Integer size) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRegistrationFormEntity r ")
                    .append("SET r.idCustomer = :idCustomers, r.numberOfAdult = :numberOfAdult ")
                    .append("WHERE r.idDelegation = :idDelegation AND r.type = :type AND r.status = :status ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("idDelegation", idDelegation)
                    .setParameter("idCustomers", idCustomers)
                    .setParameter("type", TypeRegister.BOOK_ROOM.getValue())
                    .setParameter("status", BookingType.DELEGATION.getValue())
                    .setParameter("numberOfAdult", size);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("updateByIdDelegation QLKSRegistrationFormEntity failed Id: [{}]", idDelegation, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void save(QLKSRegistrationFormEntity entity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(entity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSRegistrationFormEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(entity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(QLKSRegistrationFormEntity entity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.update(entity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Update QLKSRegistrationFormEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(entity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRegistrationFormEntity r ")
                    .append("SET r.isDelete = :isDelete ")
                    .append("WHERE r.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSRegistrationFormEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRegistrationFormEntity> getById(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRegistrationFormEntity ")
                    .append("WHERE isDelete = :isDeleted AND id = :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getById QLKSRegistrationFormEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRegistrationFormEntity> getByIdRoomAndTime(String idRoom, Long time) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRegistrationFormEntity ")
                    .append("WHERE id_room like :idRoom AND ( :time BETWEEN checkInDate AND checkOutDate) AND isDelete = false AND status not in :status ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("time", time)
                    .setParameter("status", Arrays.asList(TypeRegister.CANCEL.getValue(), TypeRegister.CHECK_OUT.getValue()))
                    .setParameter("idRoom", "%" + idRoom + "%");


            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getById QLKSRegistrationFormEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRegistrationFormEntity> getByIdRoomAndTime(String idRoom, Long timeStart, Long timeEnd) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRegistrationFormEntity ")
                    .append("WHERE id_room like :idRoom AND (( :timeStart BETWEEN checkInDate AND checkOutDate) " +
                            "OR ( :timeEnd BETWEEN checkInDate AND checkOutDate) " +
                            "OR ( checkInDate BETWEEN :timeStart AND :timeEnd) " +
                            "OR ( checkOutDate BETWEEN :timeStart AND :timeEnd)) " +
                            "AND isDelete = FALSE AND status <> :status ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("timeStart", timeStart)
                    .setParameter("timeEnd", timeEnd)
                    .setParameter("status", TypeRegister.CANCEL.getValue())
                    .setParameter("idRoom", "%" + idRoom + "%");


            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getById QLKSRegistrationFormEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void updateChangeRoom(String idRegistration, String idRoomOld, String idRoomNew) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRegistrationFormEntity r ")
                    .append("SET r.idRoom = REPLACE(r.idRoom, :idRoomOld, :idRoomNew) ")
                    .append("WHERE r.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("idRoomOld", idRoomOld)
                    .setParameter("idRoomNew", idRoomNew)
                    .setParameter("id", idRegistration);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSRegistrationFormEntity failed Id: [{}]", idRegistration, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
