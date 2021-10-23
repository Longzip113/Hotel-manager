package com.hotelManager.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateUtils {

    public static Transaction beginTransaction(Session session) {
        if (session == null) {
            throw new NullPointerException("Session is null");
        }
        return session.beginTransaction();
    }

    public static void closeSession(Session session) {
        if (session == null) {
            throw new NullPointerException("Session is null");
        }
        session.close();
    }
}
