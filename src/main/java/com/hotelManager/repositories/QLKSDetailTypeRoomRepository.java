package com.hotelManager.repositories;

import com.hotelManager.dtos.request.DetailTypeRoomRequest;
import com.hotelManager.entities.QLKSDetailTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDetailTypeRoomModel;

import java.util.List;
import java.util.Optional;

public interface QLKSDetailTypeRoomRepository {

    void save(QLKSDetailTypeRoomEntity qlksDetailTypeRoomEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id ,DetailTypeRoomRequest request) throws HotelManagerException;

    Optional<QLKSDetailTypeRoomEntity> getById(String id) throws HotelManagerException;

    List<QLKSDetailTypeRoomModel> getByIdTypeRoom(String id);

    Optional<QLKSDetailTypeRoomEntity> getByIdTypeRoomAndIdDetail(String idtype, String idDetail, Integer detailType) throws HotelManagerException;

}
