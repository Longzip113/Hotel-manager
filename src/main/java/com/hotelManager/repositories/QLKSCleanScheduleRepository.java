package com.hotelManager.repositories;

import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.entities.QLKSCleanScheduleEntity;
import com.hotelManager.exceptions.HotelManagerException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface QLKSCleanScheduleRepository {

    void batchSave(List<QLKSCleanScheduleEntity> qlksRoomArrangementEntities, Session session);

    List<QLKSCleanScheduleEntity> getAll (CleanScheduleRequest request) throws HotelManagerException;

    Optional<QLKSCleanScheduleEntity> getByEmployeeAndRoom(String idEmployee, String idRoom, String day) throws HotelManagerException;

    void update(QLKSCleanScheduleEntity entity) throws HotelManagerException;
}
