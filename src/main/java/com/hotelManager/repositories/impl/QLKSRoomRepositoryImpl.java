package com.hotelManager.repositories.impl;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.RoomRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.QLKSRoomRepository;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSRoomRepositoryImpl implements QLKSRoomRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String save(QLKSRoomEntity qlksRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            String id = (String)session.save(qlksRoomEntity);
            session.getTransaction().commit();
            return id;
        } catch (PersistenceException e) {

            log.error("Save QLKSRoomEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksRoomEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void delete(String idRoom) {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRoomEntity r ")
                    .append("SET r.isDelete = :isDeleted ")
                    .append("WHERE r.id = :idRoom");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.TRUE)
                    .setParameter("idRoom", idRoom);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(RoomRequest roomRequest, String idRoom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRoomEntity r ")
                    .append("SET r.nameRoom = :nameRoom, r.description = :description ");
            if (StringUtils.isNotBlank(roomRequest.getIdTypeRoom())) {
                hql.append(", r.idTypeRoom = :idTypeRoom ");
            }
            hql.append("WHERE r.id = :idRoom AND r.isDelete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("nameRoom", roomRequest.getNameRoom())
                    .setParameter("description", roomRequest.getDescription())
                    .setParameter("idRoom", idRoom)
                    .setParameter("isDeleted", Boolean.FALSE);

            if (StringUtils.isNotBlank(roomRequest.getIdTypeRoom())) {
                query.setParameter("idTypeRoom", roomRequest.getIdTypeRoom());
            }

            if (query.executeUpdate() < 1) {

                log.error("Update QLKSRoomEntity fail !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void updateBatch(List<QLKSRoomEntity> listEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();

        final int batchSize = Constants.BATCH_SIZE;
        final int length = listEntity.size();

        for (int i = 0; i < length; i++) {
            if (i > 0 && i % batchSize == 0) {
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }

            session.update(listEntity.get(i));
        }
    }

    @Override
    public Optional<QLKSRoomEntity> getByNameRoom(String nameRoom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRoomEntity r ")
                    .append("WHERE r.nameRoom = :nameRoom AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("nameRoom", nameRoom);

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
    public List<QLKSRoomModel> getAll(String sortBy, String sortOrder) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT r.id_room, r.name_room, r.description, tr.name_type_room, e.name_employee, r.housekeeping_order, r.id_type_room, r.status ")
                    .append("FROM qlks_room r ")
                    .append("LEFT JOIN qlks_type_room tr ON tr.id_type_room = r.id_type_room ")
                    .append("LEFT JOIN qlks_employee e ON e.id_employee = r.id_housekeeping_staff ")
                    .append("WHERE r.is_delete = :isDeleted ")
                    .append(buildSqlSortGetAll(sortBy))
                    .append(sortOrder.equals(Constants.SORT_OR_DER_ASC) ? "ASC " : "DESC ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSRoomModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE);

            return query.getResultList();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSRoomEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSRoomEntity WHERE isDelete = :idDelete ");

            log.info("SQL [{}]", hql);

            Query<QLKSRoomEntity> query = session.createQuery(hql.toString(), QLKSRoomEntity.class)
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
    public Optional<QLKSRoomModel> getByIdRoom(String idRoom) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT r.id_room, r.name_room, r.description, r.status, tr.name_type_room, e.name_employee, r.housekeeping_order, r.id_type_room ")
                    .append("FROM qlks_room r ")
                    .append("LEFT JOIN qlks_type_room tr ON tr.id_type_room = r.id_type_room ")
                    .append("LEFT JOIN qlks_employee e ON e.id_employee = r.id_housekeeping_staff ")
                    .append("WHERE r.is_delete = :isDeleted AND r.id_room = :idRoom");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSRoomModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("idRoom", idRoom);

            return query.uniqueResultOptional();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRoomEntity> getByIdRoomEntity(String idRoom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSRoomEntity r ")
                    .append("WHERE r.id = :id AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("id", idRoom);

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
    public List<QLKSRoomModel> getByIds(List<String> id) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT r.id_room, r.name_room, r.description, r.status, tr.name_type_room, e.name_employee, r.housekeeping_order, r.id_type_room ")
                    .append("FROM qlks_room r ")
                    .append("LEFT JOIN qlks_type_room tr ON tr.id_type_room = r.id_type_room ")
                    .append("LEFT JOIN qlks_employee e ON e.id_employee = r.id_housekeeping_staff ")
                    .append("WHERE r.is_delete = :isDeleted AND r.id_room in :ids ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSRoomModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("ids", id);

            return query.getResultList();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public long priceRooms(List<String> idRooms) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT SUM(tr.price) ")
                    .append("FROM qlks_room r ")
                    .append("LEFT JOIN qlks_type_room tr ON tr.id_type_room = r.id_type_room ")
                    .append("WHERE r.id_room in :ids AND r.is_delete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("ids", idRooms);

            BigDecimal count = (BigDecimal)query.uniqueResult();
            return count.longValue();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void updateStatus(List<String> idRoom, Integer status) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSRoomEntity r ")
                    .append("SET r.status = :status ")
                    .append("WHERE r.id in :idRoom AND r.isDelete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("status", status)
                    .setParameter("idRoom", idRoom)
                    .setParameter("isDeleted", Boolean.FALSE);

            if (query.executeUpdate() < 1) {

                log.error("Update QLKSRoomEntity fail !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update QLKSRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    private String buildSqlSortGetAll(String sortBy) {
        switch (sortBy) {
            case "5":
                return "ORDER BY e.name_employee ";
            case "2":
                return "ORDER BY tr.name_type_room ";
            case "3":
                return "ORDER BY r.status ";
            case "4":
                return "ORDER BY r.housekeeping_order ";
            default:
                return "ORDER BY r.name_room ";
        }
    }
}
