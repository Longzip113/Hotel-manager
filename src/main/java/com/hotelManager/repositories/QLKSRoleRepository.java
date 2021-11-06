package com.hotelManager.repositories;

import com.hotelManager.dtos.request.UpdateRoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSRoleRepository {
    List<QLKSRoleEntity> getAll() throws HotelManagerException;

    void save(QLKSRoleEntity qlksRoleEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateRoleRequest updateRoleRequest) throws HotelManagerException;

    Optional<QLKSRoleEntity> getById(String id) throws HotelManagerException;

    Optional<QLKSRoleEntity> getByCode(String code) throws HotelManagerException;

    Optional<QLKSRoleEntity> getByNameOrCode(String name, String code, String id) throws HotelManagerException;

    long countRoleByEmployee(String id);
}
