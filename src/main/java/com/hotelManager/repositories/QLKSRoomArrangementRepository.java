package com.hotelManager.repositories;

import com.hotelManager.entities.QLKSRoomArrangementEntity;
import com.hotelManager.exceptions.HotelManagerException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface QLKSRoomArrangementRepository {

    void batchSave(List<QLKSRoomArrangementEntity> qlksRoomArrangementEntities, Session session);

    Optional<QLKSRoomArrangementEntity> getByIdRegisterAndCustomer(String idRegister, String idCustomer) throws HotelManagerException;

    Optional<QLKSRoomArrangementEntity> getByIdRegisterAndRoom(String idRegister, String idCustomer) throws HotelManagerException;

}
