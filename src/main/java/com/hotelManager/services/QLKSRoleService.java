package com.hotelManager.services;

import com.hotelManager.dtos.request.AddRoleRequest;
import com.hotelManager.dtos.request.UpdateRoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSRoleService {

    List<QLKSRoleEntity> getAll() throws HotelManagerException;

    void add(AddRoleRequest addRoleRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateRoleRequest updateRoleRequest) throws HotelManagerException;

    QLKSRoleEntity getDetail(String id) throws HotelManagerException;

}
