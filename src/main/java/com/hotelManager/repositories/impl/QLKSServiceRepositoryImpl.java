package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.UpdateServiceRequest;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSServiceRepository;
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
public class QLKSServiceRepositoryImpl implements QLKSServiceRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSServiceEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSServiceEntity WHERE isDelete = :isDelete ");

            log.info("SQL [{}]", hql);

            Query<QLKSServiceEntity> query = session.createQuery(hql.toString(), QLKSServiceEntity.class)
                    .setParameter("isDelete", Boolean.FALSE);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("Get QLKSServiceEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void save(QLKSServiceEntity qlksTypeRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksTypeRoomEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSServiceEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksTypeRoomEntity), e);
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
                    .append("UPDATE QLKSServiceEntity r ")
                    .append("SET r.isDelete = :isDelete ")
                    .append("WHERE r.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSServiceEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(String id, UpdateServiceRequest updateServiceRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSServiceEntity ")
                    .append("SET ")
                    .append("nameService = :nameService, ")
                    .append("price = :price ")
                    .append("WHERE id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("id", id)
                    .setParameter("nameService", updateServiceRequest.getNameService())
                    .setParameter("price", updateServiceRequest.getPrice());

            query.executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Update QLKSServiceEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSServiceEntity> getById(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSServiceEntity ")
                    .append("WHERE id = :idType AND isDelete = :isDelete ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("idType", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSServiceEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSServiceEntity> getByNameService(String nameService) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSServiceEntity ")
                    .append("WHERE nameService = :nameService AND isDelete = :isDelete ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("nameService", nameService);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetByNameService QLKSServiceEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSServiceEntity> getByNameServiceAndPrice(String nameService, Integer price) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSServiceEntity ")
                    .append("WHERE price = :price AND nameService = :nameService AND isDelete = :isDelete ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("price", price)
                    .setParameter("nameService", nameService);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public long countCustomByService(String idService) {
        return 1l;
    }
}
