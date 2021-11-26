package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.QLKSLogCustomerEntity;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSLogCustomerRepository;
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
public class QLKSLogCustomerRepositoryImpl implements QLKSLogCustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(QLKSLogCustomerEntity entity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(entity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSLogCustomerEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(entity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
