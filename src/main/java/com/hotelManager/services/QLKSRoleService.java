package com.hotelManager.services;

import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;

public interface QLKSRoleService {

    List<QLKSRoleEntity> getAll() throws HotelManagerException;

    void add(RoleRequest roleRequest) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, RoleRequest roleRequest) throws HotelManagerException;

    QLKSRoleEntity getDetail(String id) throws HotelManagerException;

}
