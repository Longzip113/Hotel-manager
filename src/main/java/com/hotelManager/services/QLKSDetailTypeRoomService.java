package com.hotelManager.services;

import com.hotelManager.dtos.request.DetailTypeRoomRequest;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSDetailTypeRoomService {

    void add(DetailTypeRoomRequest request) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, DetailTypeRoomRequest request) throws HotelManagerException;

}
