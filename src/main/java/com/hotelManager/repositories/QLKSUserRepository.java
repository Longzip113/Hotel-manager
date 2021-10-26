package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSUserEntity;
import com.hotelManager.exceptions.HotelManagerException;
import org.hibernate.Session;

import java.util.Optional;

public interface QLKSUserRepository {

    void save(QLKSUserEntity qlksUserEntity, Session session) throws HotelManagerException;
}
