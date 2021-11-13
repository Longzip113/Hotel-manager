package com.hotelManager.repositories;

import com.hotelManager.dtos.request.DelegationRequest;
import com.hotelManager.entities.QLKSDelegationEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSDelegationModel;

import java.util.List;
import java.util.Optional;

public interface QLKSDelegationRepository {
    List<QLKSDelegationModel> getAll() throws HotelManagerException;

    void save(QLKSDelegationEntity qlksDelegationEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, DelegationRequest updateDelegationRequest) throws HotelManagerException;

    Optional<QLKSDelegationModel> getById(String id) throws HotelManagerException;

    Optional<QLKSDelegationEntity> getByName(String name, String id) throws HotelManagerException;
}
