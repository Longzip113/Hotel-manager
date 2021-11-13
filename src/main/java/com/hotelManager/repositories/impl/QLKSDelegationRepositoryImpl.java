package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.DelegationRequest;
import com.hotelManager.entities.QLKSDelegationEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDelegationModel;
import com.hotelManager.repositories.QLKSDelegationRepository;
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
public class QLKSDelegationRepositoryImpl implements QLKSDelegationRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<QLKSDelegationModel> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT d.id_delegations, d.name_delegations, c.name as 'nameManager', d.id_customer, d.name_company, d.number_of_people ")
                    .append("FROM qlks_delegation d ")
                    .append("LEFT JOIN qlks_customer c ON d.id_team_manager = c.id_customer ")
                    .append("WHERE d.is_delete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSDelegationModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE);

            return query.getResultList();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void save(QLKSDelegationEntity qlksDelegationEntity) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {

            HibernateUtils.beginTransaction(session);
            session.save(qlksDelegationEntity);
            session.getTransaction().commit();

        } catch (PersistenceException e) {

            log.error("Save QLKSDelegationEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksDelegationEntity), e);
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
                    .append("UPDATE QLKSDelegationEntity r ")
                    .append("SET r.isDelete = :isDeleted ")
                    .append("WHERE r.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(String id, DelegationRequest updateDelegationRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);

        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSDelegationEntity ")
                    .append("SET ")
                    .append("nameDelegations = :nameDelegations, ")
                    .append("nameCompany = :nameCompany, ")
                    .append("idTeamManager = :idTeamManager ")
                    .append("WHERE id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("id", id)
                    .setParameter("nameDelegations", updateDelegationRequest.getNameDelegations())
                    .setParameter("idTeamManager", updateDelegationRequest.getIdTeamManager())
                    .setParameter("nameCompany", updateDelegationRequest.getNameCompany());

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
    public Optional<QLKSDelegationModel> getById(String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT d.id_delegations, d.name_delegations, c.name as 'nameManager', d.id_customer, d.name_company, d.number_of_people ")
                    .append("FROM qlks_delegation d ")
                    .append("LEFT JOIN qlks_customer c ON d.id_team_manager = c.id_customer ")
                    .append("WHERE d.is_delete = :isDeleted AND d.id_delegations = :id ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSDelegationModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSDelegationEntity> getByName(String name, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSDelegationEntity r ")
                    .append("WHERE r.nameDelegations = :nameDelegations AND r.isDelete = :isDelete AND r.id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDelete", Boolean.FALSE)
                    .setParameter("id", id)
                    .setParameter("nameDelegations", name);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("GetById QLKSDelegationEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }
}
