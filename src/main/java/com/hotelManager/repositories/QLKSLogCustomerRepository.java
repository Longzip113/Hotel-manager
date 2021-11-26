package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSLogCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;


public interface QLKSLogCustomerRepository {

    void save(QLKSLogCustomerEntity entity) throws HotelManagerException;
}
