package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.DetailTypeRoomRequest;
import com.hotelManager.dtos.request.TypeRoomRequest;
import com.hotelManager.entities.QLKSDetailTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDetailTypeRoomModel;
import com.hotelManager.repositories.QLKSDetailTypeRoomRepository;
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
public class QLKSDetailTypeRoomRepositoryImpl implements QLKSDetailTypeRoomRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(QLKSDetailTypeRoomEntity qlksDetailTypeRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksDetailTypeRoomEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSDetailTypeRoomEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksDetailTypeRoomEntity), e);
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
                    .append("UPDATE QLKSDetailTypeRoomEntity r ")
                    .append("SET r.isDelete = :isDeleted ")
                    .append("WHERE r.id = :id ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSTypeRoomEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(String id ,DetailTypeRoomRequest request) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSDetailTypeRoomEntity ")
                    .append("SET ")
                    .append("quantity = :quantity, ")
                    .append("idType = :idType, ")
                    .append("idDetail = :idDetail, ")
                    .append("typeDetail = :typeDetail ")
                    .append("WHERE id = :id AND isDelete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("idType", request.getIdTypeRoom())
                    .setParameter("idDetail", request.getIdDetailType())
                    .setParameter("typeDetail", request.getTypeDetail())
                    .setParameter("quantity", request.getQuantity())
                    .setParameter("id", id)
                    .setParameter("isDeleted", Boolean.FALSE);

            query.executeUpdate();
            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Update QLKSTypeRoomEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSDetailTypeRoomEntity> getById(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSDetailTypeRoomEntity r ")
                    .append("WHERE r.id = :id AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSDetailTypeRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSDetailTypeRoomModel> getByIdTypeRoom(String id) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT dtt.id_type_room, dtt.type_detail, IFNULL(hd.name_hotel_device, s.name_service) AS 'name', dtt.id_detail, dtt.quantity ")
                    .append("FROM qlks_detail_type_room dtt ")
                    .append("LEFT JOIN qlks_service s ON s.id_service = dtt.id_detail ")
                    .append("LEFT JOIN qlks_hotel_device hd ON hd.id_hotel_device = dtt.id_detail ")
                    .append("WHERE dtt.id_type = :idType AND  dtt.is_delete = :isDeleted ")
                    .append("ORDER BY dtt.type_detail ASC ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSDetailTypeRoomModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("idType", id);

            return query.getResultList();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSDetailTypeRoomEntity> getByIdTypeRoomAndIdDetail(String idtype, String idDetail, Integer detailType) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSDetailTypeRoomEntity r ")
                    .append("WHERE r.idType = :idType AND r.idDetail = :idDetail AND r.typeDetail = :typeDetail AND r.isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("idType", idtype)
                    .setParameter("idDetail", idDetail)
                    .setParameter("typeDetail", detailType);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByIdTypeRoomAndIdDetail QLKSDetailTypeRoomEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }
}
