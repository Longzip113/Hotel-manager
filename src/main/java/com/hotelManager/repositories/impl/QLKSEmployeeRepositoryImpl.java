package com.hotelManager.repositories.impl;

import com.hotelManager.dtos.request.ChangePasswordRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.dtos.request.UpdateUserRequest;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.repositories.QLKSEmployeeRepository;
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

import static com.hotelManager.constants.Constants.PASSWORD_DEFAULT;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_VERIFICATION;

@Repository
@Slf4j
public class QLKSEmployeeRepositoryImpl implements QLKSEmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<QLKSEmployeeModel> getAll() throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT e.id_employee, e.name_employee, e.gender, e.identity_card, e.address, e.phone_number, e.email, r.code_role, r.name_role ")
                    .append("FROM qlks_employee e ")
                    .append("LEFT JOIN qlks_role r ON r.id_role = e.id_role ")
                    .append("WHERE e.is_delete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSEmployeeModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE);

            return query.getResultList();
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
                    .append("UPDATE QLKSEmployeeEntity e ")
                    .append("SET e.isDelete = :isDeleted ")
                    .append("WHERE e.id = :id");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.TRUE)
                    .setParameter("id", id);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (PersistenceException e) {

            log.error("Delete QLKSEmployeeEntity failed Id: [{}]", id, e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void update(String id, UpdateUserRequest updateUserRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);
        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSEmployeeEntity ")
                    .append("SET nameEmployee = :nameEmployee, gender = :gender, email = :email, identityCard = :identityCard, address = :address, phoneNumber = :phoneNumber ")
                    .append("WHERE id = :id AND isDelete = :isDelete ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("nameEmployee", updateUserRequest.getNameEmployee())
                    .setParameter("gender", updateUserRequest.getGender())
                    .setParameter("email", updateUserRequest.getEmail())
                    .setParameter("identityCard", updateUserRequest.getIdentityCard())
                    .setParameter("address", updateUserRequest.getAddress())
                    .setParameter("phoneNumber", updateUserRequest.getPhoneNumber())
                    .setParameter("id", id)
                    .setParameter("isDelete", Boolean.FALSE);

            if (query.executeUpdate() < 1) {

                log.error("Update QLKSEmployeeEntity fail !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update QLKSEmployeeEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public String save(QLKSEmployeeEntity qlksEmployeeEntity, Session session) throws HotelManagerException {
        try {
            return (String) session.save(qlksEmployeeEntity);
        } catch (PersistenceException e) {

            log.error("Save QLKSEmployeeEntity failed Object: [{}]", GsonHelper.defaultInstance().toJson(qlksEmployeeEntity), e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return null;
        }
    }

    @Override
    public Optional<QLKSEmployeeModel> getById(String id) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT e.id_employee, e.name_employee, e.gender, e.identity_card, e.address, e.phone_number, e.email, r.code_role, r.name_role ")
                    .append("FROM qlks_employee e ")
                    .append("LEFT JOIN qlks_role r ON r.id_role = e.id_role ")
                    .append("WHERE e.id_employee = :id AND e.is_delete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSEmployeeModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSEmployeeModel> getEmployeeByEmailAndPassWord(String passWord, String email) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT e.id_employee, e.name_employee, e.gender, e.identity_card, e.address, e.phone_number, e.email, r.code_role, r.name_role ")
                    .append("FROM qlks_employee e ")
                    .append("LEFT JOIN qlks_role r ON r.id_role = e.id_role ")
                    .append("WHERE e.email = :email AND e.pass_word = :passWord AND e.is_delete = :isDeleted ");

            log.info("SQL [{}]", hql);

            Query query = session.createNativeQuery(hql.toString(), "QLKSEmployeeModelMapping")
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("passWord", passWord)
                    .setParameter("email", email);

            return query.uniqueResultOptional();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSEmployeeEntity> getByEmailDefId(String email, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSEmployeeEntity ")
                    .append("WHERE email = :email AND isDelete = :isDeleted AND id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("email", email)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByEmail QLKSEmployeeEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSEmployeeEntity> getByEmail(String email) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSEmployeeEntity ")
                    .append("WHERE email = :email AND isDelete = :isDeleted ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("email", email);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByEmail QLKSEmployeeEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public Optional<QLKSEmployeeEntity> getByCardDefId(String card, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("FROM QLKSEmployeeEntity ")
                    .append("WHERE identityCard = :card AND isDelete = :isDeleted AND id <> :id ");
            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("isDeleted", Boolean.FALSE)
                    .setParameter("card", card)
                    .setParameter("id", id);

            return query.uniqueResultOptional();
        } catch (Exception e) {

            log.error("getByCard QLKSEmployeeEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            return Optional.empty();
        }  finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void saveVerification(String email, String veri, String id) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);
        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSEmployeeEntity ")
                    .append("SET verification = :verification ")
                    .append("WHERE id = :id AND isDelete = :isDelete AND email = :email");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("verification", veri)
                    .setParameter("email", email)
                    .setParameter("id", id)
                    .setParameter("isDelete", Boolean.FALSE);

            if (query.executeUpdate() < 1) {

                log.error("Update QLKSEmployeeEntity fail !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update QLKSEmployeeEntity fail !", e);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVER);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public void resetPasswordByVerification(ChangePasswordRequest changePasswordRequest) throws HotelManagerException {
        Session session = sessionFactory.openSession();
        HibernateUtils.beginTransaction(session);
        try {
            StringBuilder hql = new StringBuilder()
                    .append("UPDATE QLKSEmployeeEntity ")
                    .append("SET verification = null, passWord = :passWord ")
                    .append("WHERE verification = :verification AND isDelete = :isDelete ");

            log.info("SQL [{}]", hql);

            Query query = session.createQuery(hql.toString())
                    .setParameter("verification", changePasswordRequest.getVerification())
                    .setParameter("passWord", changePasswordRequest.getPassword())
                    .setParameter("isDelete", Boolean.FALSE);

            if (query.executeUpdate() < 1) {

                log.error("Update QLKSEmployeeEntity fail !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_VERIFICATION);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Update QLKSEmployeeEntity fail !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_VERIFICATION);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
