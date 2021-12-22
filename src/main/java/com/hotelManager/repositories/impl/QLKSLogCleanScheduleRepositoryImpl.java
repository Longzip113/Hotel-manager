package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.entities.QLKSLogCleanRoomEntity;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSLogCleanScheduleRepository;
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
public class QLKSLogCleanScheduleRepositoryImpl implements QLKSLogCleanScheduleRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(QLKSLogCleanRoomEntity qlksLogCleanRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksLogCleanRoomEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSLogCleanRoomEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksLogCleanRoomEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<QLKSLogCleanRoomEntity> getAll(CleanScheduleRequest request) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSLogCleanRoomEntity ")
                    .append("WHERE 1 = 1 ")
                    .append(StringUtils.isNotBlank(request.getIdEmployee()) ? "AND idEmployee = :idEmployee " : " ")
                    .append(StringUtils.isNotBlank(request.getIdRoom()) ? "AND idRoom = :idRoom " : " ")
                    .append(StringUtils.isNotBlank(request.getDay()) ? "AND dayWorking = :dayWorking " : " ");
            ;

            log.info("SQL [{}]", hql);

            Query<QLKSLogCleanRoomEntity> query = session.createQuery(hql.toString(), QLKSLogCleanRoomEntity.class);

            if (StringUtils.isNotBlank(request.getIdEmployee())) {
                query.setParameter("idEmployee", request.getIdEmployee());
            }

            if (StringUtils.isNotBlank(request.getIdRoom())) {
                query.setParameter("idRoom", request.getIdRoom());
            }

            if (StringUtils.isNotBlank(request.getDay())) {
                query.setParameter("dayWorking", request.getDay());
            }

            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("getAll QLKSLogCleanRoomEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSLogCleanRoomEntity> getByRoomAndEmployee(String idRoom, String idEmployee) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSLogCleanRoomEntity r ")
                    .append("WHERE r.idEmployee = :idEmployee AND r.timeEnd is null AND r.idRoom = :idRoom AND r.dayWorking = :dayWork ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("idEmployee", idEmployee)
                    .setParameter("dayWork", java.time.LocalDate.now().toString())
                    .setParameter("idRoom", idRoom);

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
    public void update(QLKSLogCleanRoomEntity qlksLogCleanRoomEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.update(qlksLogCleanRoomEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSLogCleanRoomEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksLogCleanRoomEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
