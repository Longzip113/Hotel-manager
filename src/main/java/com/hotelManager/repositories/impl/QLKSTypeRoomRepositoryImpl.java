package com.hotelManager.repositories.impl;

import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
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
public class QLKSTypeRoomRepositoryImpl implements QLKSTypeRoomRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSTypeRoomEntity> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSTypeRoomEntity WHERE isDelete = :isDelete ");

            log.info("SQL [{}]", hql);

            Query<QLKSTypeRoomEntity> query = session.createQuery(hql.toString(), QLKSTypeRoomEntity.class)
                    .setParameter("isDelete", Boolean.FALSE);

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("Get one NodeTag by nodeId failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void save(QLKSTypeRoomEntity qlksTypeRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksTypeRoomEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSTypeRoomEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksTypeRoomEntity), e);
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
                    .append("UPDATE QLKSTypeRoomEntity r ")
                    .append("SET r.isDelete = :isDeleted ")
                    .append("WHERE r.id = :id");

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
    public long countRoomByTypeRoom(String idTypeRoom) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT count(*) FROM qlks_type_room tr ")
                    .append("LEFT JOIN qlks_room r ON tr.id_type_room = r.id_type_room ")
                    .append("WHERE tr.id_type_room = r.id_type_room AND tr.id_type_room = :id AND tr.is_delete = 0 AND r.is_delete = 0 ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString())
                    .setParameter("id", idTypeRoom);

            BigInteger count = (BigInteger) query.uniqueResult();
            return count.longValue();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
