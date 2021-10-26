package com.hotelManager.repositories.impl;

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

import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Repository
@Slf4j
public class QLKSEmployeeRepositoryImpl implements QLKSEmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

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
    public Optional<QLKSEmployeeModel> getEmployeeByEmailAndPassWord(String passWord, String email) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder hql = new StringBuilder()
                    .append("SELECT e.id_employee, e.name_employee, e.gender, e.identity_card, e.address, e.phone_number, us.email, r.code_role, r.name_role ")
                    .append("FROM qlks_employee e ")
                    .append("LEFT JOIN qlks_user us ON us.id_user = e.id_employee ")
                    .append("LEFT JOIN qlks_role r ON r.id_role = us.id_role ")
                    .append("WHERE us.email = :email AND us.pass_word = :passWord AND e.is_delete = :isDeleted ");

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
}
