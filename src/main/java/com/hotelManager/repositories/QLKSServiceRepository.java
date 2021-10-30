package com.hotelManager.repositories;

import com.hotelManager.dtos.request.UpdateServiceRequest;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.exceptions.HotelManagerException;

import java.util.List;
import java.util.Optional;

public interface QLKSServiceRepository {
    List<QLKSServiceEntity> getAll() throws HotelManagerException;

    void save(QLKSServiceEntity qlksServiceEntity) throws HotelManagerException;

    void delete(String id) throws HotelManagerException;

    void update(String id, UpdateServiceRequest updateServiceRequest) throws HotelManagerException;

    Optional<QLKSServiceEntity> getById(String id) throws HotelManagerException;

    Optional<QLKSServiceEntity> getByNameService(String nameService) throws HotelManagerException;

    Optional<QLKSServiceEntity> getByNameServiceAndPrice(String nameService, Integer price) throws HotelManagerException;

    long countCustomByService(String idService);
}
