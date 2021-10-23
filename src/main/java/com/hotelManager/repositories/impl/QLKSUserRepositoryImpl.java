package com.hotelManager.repositories.impl;

import com.hotelManager.Users.User;
import com.hotelManager.repositories.QLKSUserRepository;
import com.hotelManager.utils.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class QLKSUserRepositoryImpl implements QLKSUserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> getByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder sql = new StringBuilder()
                    .append("SELECT u.email, u.pass_word, r.code_role, u.is_delete ")
                    .append("FROM qlks_user u ")
                    .append("LEFT JOIN qlks_role r ON r.id_role = u.id_role ")
                    .append("WHERE u.email = :email AND u.is_delete = 0 ");

            log.info("SQL [{}]", sql);

            Query query = session.createNativeQuery(sql.toString(), "UserDTOMapping")
                    .setParameter("email", email);

            return query.uniqueResultOptional();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
