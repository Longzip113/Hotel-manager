package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.UpdateRoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRoleRepository;
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
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSRoleRepositoryImpl implements QLKSRoleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSRoleEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSRoleEntity WHERE isDelete = :idDelete ");

            log.info("SQL [{}]", hql);

            Query<QLKSRoleEntity> query = session.createQuery(hql.toString(), QLKSRoleEntity.class)
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
    public void save(QLKSRoleEntity qlksRoleEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksRoleEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSRoleEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksRoleEntity), e);
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
                    .append("UPDATE QLKSRoleEntity r ")
                    .append("SET r.isDelete = :isDelete ")
                    .append("WHERE r.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSRoleEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(String id, UpdateRoleRequest updateRoleRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRoleEntity ")
                    .append("SET ")
                    .append("nameRole = :nameRole, ")
                    .append("codeRole = :code ")
                    .append("WHERE id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("id", id)
                    .setParameter("nameRole", updateRoleRequest.getNameRole())
                    .setParameter("code", updateRoleRequest.getCode());

            query.executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Update QLKSRoleEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRoleEntity> getById(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRoleEntity r ")
                    .append("WHERE r.id = :id AND r.isDelete = :isDelete ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSRoleEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRoleEntity> getByCode(String code) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRoleEntity r ")
                    .append("WHERE r.codeRole = :code AND r.isDelete = :isDelete ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("code", code);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSRoleEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRoleEntity> getByNameOrCode(String name, String code, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRoleEntity r ")
                    .append("WHERE (r.nameRole = :nameRole OR r.codeRole = :code) AND r.isDelete = :isDelete AND r.id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("code", code)
                    .setParameter("id", id)
                    .setParameter("nameRole", name);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetByNameAndCode QLKSRoleEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public long countRoleByEmployee(String id) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT count(*) FROM qlks_employee e ")
                    .append("LEFT JOIN SELECT * FROM qlks_role r ON r.id_role = e.id_role ")
                    .append("WHERE e.id_role = :id AND r.is_delete = 0 AND e.is_delete = 0 ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString())
                    .setParameter("id", id);

            BigInteger count = (BigInteger) query.uniqueResult();
            return count.longValue();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
