package com.hotelManager.repositories.impl;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.entities.QLKSRoomArrangementEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRoleRepository;
import com.hotelManager.repositories.QLKSRoomArrangementRepository;
import com.hotelManager.utils.GsonHelper;
import com.hotelManager.utils.HibernateUtils;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ReplicationMode;
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
public class QLKSRoomArrangementRepositoryImpl implements QLKSRoomArrangementRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void batchSave(List<QLKSRoomArrangementEntity> qlksRoomArrangementEntities, Session session) {
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
    public Optional<QLKSRoomArrangementEntity> getByIdRegisterAndCustomer(String idRegister, String idCustomer) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSRoomArrangementEntity WHERE idRegistrationForm = :idRegistrationForm AND idCustomer like :idCustomer ");

            log.info("SQL [{}]", hql);

            Query<QLKSRoomArrangementEntity> query = session.createQuery(hql.toString(), QLKSRoomArrangementEntity.class)
                    .setParameter("idRegistrationForm", idRegister)
                    .setParameter("idCustomer", "%" + idCustomer + "%");

            return query.uniqueResultOptional();
        } catch (PersistenceException e) {
            log.error("getAll QLKSRoleEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSRoomArrangementEntity> getByIdRegisterAndRoom(String idRegister, String idRoom) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder().append("FROM QLKSRoomArrangementEntity WHERE idRegistrationForm = :idRegistrationForm AND idRoom = :idRoom ");

            log.info("SQL [{}]", hql);

            Query<QLKSRoomArrangementEntity> query = session.createQuery(hql.toString(), QLKSRoomArrangementEntity.class)
                    .setParameter("idRegistrationForm", idRegister)
                    .setParameter("idRoom", idRoom);

            return query.uniqueResultOptional();
        } catch (PersistenceException e) {
            log.error("getAll QLKSRoleEntity failed!!", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
