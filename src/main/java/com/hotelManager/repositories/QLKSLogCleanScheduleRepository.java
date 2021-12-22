package com.hotelManager.repositories;

import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.entities.QLKSLogCleanRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSLogCleanScheduleRepository {

    void save(QLKSLogCleanRoomEntity qlksLogCleanRoomEntity) throws HotelManagerException;

    List<QLKSLogCleanRoomEntity> getAll (CleanScheduleRequest request) throws HotelManagerException;

    Optional<QLKSLogCleanRoomEntity> getByRoomAndEmployee(String idRoom, String idEmployee) throws HotelManagerException;

    void update(QLKSLogCleanRoomEntity qlksLogCleanRoomEntity) throws HotelManagerException;
}
