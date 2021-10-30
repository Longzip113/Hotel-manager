package com.hotelManager.repositories.impl;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.UpdateCustomerRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSCustomerRepository;
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
public class QLKSCustomerRepositoryImpl implements QLKSCustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(QLKSCustomerEntity qlksCustomerEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksCustomerEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSCustomerEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksCustomerEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void delete(String idCustom) {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSCustomerEntity ")
                    .append("SET isDelete = :isDeleted ")
                    .append("WHERE id = :idRoom");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.TRUE)
                    .setParameter("idRoom", idCustom);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(UpdateCustomerRequest customerRequest, String idCustom) throws HotelManagerException {

        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSCustomerEntity ")
                    .append("SET nationality = :nationality, phone = :phone, email = :email, card = :card, name = :name ")
                    .append("WHERE id = :id AND isDelete = :isDelete ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("nationality", customerRequest.getNationality())
                    .setParameter("phone", customerRequest.getPhoneNumber())
                    .setParameter("email", customerRequest.getEmail())
                    .setParameter("card", customerRequest.getIdentityCard())
                    .setParameter("name", customerRequest.getNameCustomer())
                    .setParameter("id", idCustom)
                    .setParameter("isDelete", Boolean.FALSE);

            if (query.executeUpdate() < 1) {

                log.error("Update QLKSCustomerEntity fail !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update QLKSCustomerEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSCustomerEntity> getByPhoneNumber(String phone, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCustomerEntity ")
                    .append("WHERE phone = :phone AND isDelete = :isDeleted AND id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("phone", phone)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByNameRoom QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSCustomerEntity> getByEmail(String email, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCustomerEntity ")
                    .append("WHERE email = :email AND isDelete = :isDeleted AND id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("email", email)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByNameRoom QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSCustomerEntity> getByCard(String card, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCustomerEntity ")
                    .append("WHERE card = :card AND isDelete = :isDeleted AND id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("card", card)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByNameRoom QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSCustomerEntity> getAll(String sortBy, String sortOrder) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCustomerEntity ")
                    .append("WHERE isDelete = :isDeleted ")
                    .append(sortBy.equals("1") ? "ORDER BY name " : "ORDER BY email ")
                    .append(sortOrder.equals(Constants.SORT_OR_DER_ASC) ? "ASC " : "DESC ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE);

            return query.getResultList();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSCustomerEntity> getById(String idCustom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCustomerEntity ")
                    .append("WHERE id = :id AND isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("id", idCustom);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByNameRoom QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }
}
