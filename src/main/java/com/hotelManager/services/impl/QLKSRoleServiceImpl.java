package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRoleRepository;
import com.hotelManager.services.QLKSRoleService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSRoleServiceImpl implements QLKSRoleService {

    @Autowired
    QLKSRoleRepository qlksRoleRepository;


    @Override
    public List<QLKSRoleEntity> getAll() throws HotelManagerException {



        return qlksRoleRepository.getAll();
    }

    @Override
    public void add(RoleRequest roleRequest) throws HotelManagerException {

        Optional<QLKSRoleEntity> qlksRoleEntity = qlksRoleRepository.getByNameOrCode(roleRequest.getNameRole(),
                roleRequest.getCode(), "");

        if (qlksRoleEntity.isPresent()) {

            log.error("Code or Name existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROLE_ALREADY_EXISTED);
        }
        QLKSRoleEntity entity = QLKSRoleEntity.builder()
                .codeRole(roleRequest.getCode())
                .nameRole(roleRequest.getNameRole())
                .isDelete(Boolean.FALSE)
                .build();

        qlksRoleRepository.save(entity);
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Optional<QLKSRoleEntity> qlksRoleEntity = qlksRoleRepository.getById(id);
        if (qlksRoleEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (qlksRoleRepository.countRoleByEmployee(id) > 0) {
            log.error("Delete Role failed Id: [{}]", id);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROLE_ALREADY);
        }

        qlksRoleRepository.delete(id);
    }

    @Override
    public void update(String id, RoleRequest roleRequest) throws HotelManagerException {
        Optional<QLKSRoleEntity> qlksRoleEntity = qlksRoleRepository.getById(id);
        if (qlksRoleEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (qlksRoleRepository.getByNameOrCode(roleRequest.getNameRole(),
                roleRequest.getCode(), id).isPresent()) {

            log.error("Code or Name existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROLE_ALREADY_EXISTED);
        }

        qlksRoleRepository.update(id, roleRequest);
    }

    @Override
    public QLKSRoleEntity getDetail(String id) throws HotelManagerException {
        Optional<QLKSRoleEntity> qlksHotelDeviceEntity = qlksRoleRepository.getById(id);
        if (qlksHotelDeviceEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        return qlksHotelDeviceEntity.get();
    }
}
