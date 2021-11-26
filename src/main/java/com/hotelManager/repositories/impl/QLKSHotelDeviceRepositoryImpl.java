package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.HotelDeviceRequest;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSHotelDeviceRepository;
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
public class QLKSHotelDeviceRepositoryImpl implements QLKSHotelDeviceRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSHotelDeviceEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSHotelDeviceEntity WHERE isDelete = :isDelete ");

            log.info("SQL [{}]", hql);

            Query<QLKSHotelDeviceEntity> query = session.createQuery(hql.toString(), QLKSHotelDeviceEntity.class)
                    .setParameter("isDelete", Boolean.FALSE);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("Get QLKSHotelDeviceEntity by nodeId failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public String save(QLKSHotelDeviceEntity qlksHotelDeviceEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            String id = (String) session.save(qlksHotelDeviceEntity);
            session.getTransaction().commit();

            return id;

        } catch (PersistenceException e) {

            log.error("Save QLKSHotelDeviceEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksHotelDeviceEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }

        return null;
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSHotelDeviceEntity r ")
                    .append("SET r.isDelete = :isDeleted ")
                    .append("WHERE r.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSHotelDeviceEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(String id, HotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSHotelDeviceEntity ")
                    .append("SET ")
                    .append("nameHotelDevice = :nameHotelDevice, ")
                    .append("price = :price, ")
                    .append("quantity = :quantity ")
                    .append("WHERE id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("id", id)
                    .setParameter("quantity", updateHotelDeviceRequest.getQuantity())
                    .setParameter("nameHotelDevice", updateHotelDeviceRequest.getNameHotelDevice())
                    .setParameter("price", updateHotelDeviceRequest.getPrice());

            query.executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Update QLKSHotelDeviceEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSHotelDeviceEntity> getById(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSHotelDeviceEntity r ")
                    .append("WHERE r.id = :idType AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("idType", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSHotelDeviceEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSHotelDeviceEntity> getByName(String name) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSHotelDeviceEntity r ")
                    .append("WHERE r.nameHotelDevice = :nameHotelDevice AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("nameHotelDevice", name);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSHotelDeviceEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSHotelDeviceEntity> getByNameAndPrice(String name, Integer price) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSHotelDeviceEntity r ")
                    .append("WHERE r.price = :price AND r.nameHotelDevice = :nameHotelDevice AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("price", price)
                    .setParameter("nameHotelDevice", name);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByNameAndPrice QLKSHotelDeviceEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public long countRoomByHotelDevice(String id) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT count(*) FROM qlks_detail_type_room tr ")
                    .append("JOIN qlks_hotel_device r ON tr.id_detail = r.id_hotel_device ")
                    .append("WHERE tr.id_detail = r.id_hotel_device AND tr.id_detail = :id AND r.is_delete = 0 ");

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
