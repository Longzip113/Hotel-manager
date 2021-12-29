package com.hotelManager.repositories.impl;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.entities.QLKSCleanScheduleEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSCleanScheduleRepository;
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
public class QLKSCleanScheduleRepositoryImpl implements QLKSCleanScheduleRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void batchSave(List<QLKSCleanScheduleEntity> qlksRoomArrangementEntities, Session session) {
        final int batchSize = Constants.BATCH_SIZE;
        final int length = qlksRoomArrangementEntities.size();

        for (int i = 0; i < length; i++) {
            if (i > 0 && i % batchSize == 0) {
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }

            session.save(qlksRoomArrangementEntities.get(i));
        }
    }

    @Override
    public List<QLKSCleanScheduleEntity> getAll(CleanScheduleRequest request) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCleanScheduleEntity ")
                    .append("WHERE 1 = 1 ")
                    .append(StringUtils.isNotBlank(request.getIdEmployee()) ? "AND idEmployee = :idEmployee " : " ")
                    .append(StringUtils.isNotBlank(request.getIdRoom()) ? "AND idRoom = :idRoom " : " ")
                    .append(StringUtils.isNotBlank(request.getDay()) ? "AND dayWorking = :dayWorking " : " ");

            log.info("SQL [{}]", hql);

            Query<QLKSCleanScheduleEntity> query = session.createQuery(hql.toString(), QLKSCleanScheduleEntity.class);

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
            log.error("getAll QLKSCleanScheduleEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSCleanScheduleEntity> getByEmployeeAndRoom(String idEmployee, String idRoom, String day) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSCleanScheduleEntity c ")
                    .append("WHERE c.idEmployee = :idEmployee AND c.idRoom = :idRoom " +
                            "AND c.dayWorking = :dayWorking ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("idEmployee", idEmployee)
                    .setParameter("idRoom", idRoom)
                    .setParameter("dayWorking", day);

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
    public void update(QLKSCleanScheduleEntity entity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.update(entity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSRoleEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(entity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
