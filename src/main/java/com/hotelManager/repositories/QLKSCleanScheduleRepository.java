package com.hotelManager.repositories;

import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.dtos.request.GetViewCleanScheduleRequest;
import com.hotelManager.entities.QLKSCleanScheduleEntity;
import com.hotelManager.exceptions.HotelManagerException;
import org.hibernate.Session;

import java.util.List;

public interface QLKSCleanScheduleRepository {

    void batchSave(List<QLKSCleanScheduleEntity> qlksRoomArrangementEntities, Session session);

    List<QLKSCleanScheduleEntity> getAll (CleanScheduleRequest request) throws HotelManagerException;

    List<QLKSCleanScheduleEntity> getAllViewRoom (GetViewCleanScheduleRequest request) throws HotelManagerException;
}
