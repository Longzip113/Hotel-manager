package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.AddServiceRequest;
import com.hotelManager.dtos.request.AddTypeRoomRequest;
import com.hotelManager.dtos.request.UpdateServiceRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSServiceRepository;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.services.QLKSServiceService;
import com.hotelManager.services.QLKSTypeRoomService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSServiceServiceImpl implements QLKSServiceService {

    @Autowired
    QLKSServiceRepository qlksServiceRepository;


    @Override
    public List<QLKSServiceEntity> getAll() throws HotelManagerException {
        return qlksServiceRepository.getAll();
    }

    @Override
    public void addService(AddServiceRequest addServiceRequest) throws HotelManagerException {
        if (qlksServiceRepository.getByNameService(addServiceRequest.getNameService()).isPresent()) {
            log.error("Name existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVICE_ALREADY_EXISTED);
        }

        QLKSServiceEntity qlksServiceEntity = QLKSServiceEntity.builder()
                .nameService(addServiceRequest.getNameService())
                .price(addServiceRequest.getPrice())
                .isDelete(Boolean.FALSE)
                .build();

        qlksServiceRepository.save(qlksServiceEntity);
    }

    @Override
    public void deleteService(String id) throws HotelManagerException {
        if (qlksServiceRepository.getById(id).isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVICE_NOT_EXISTED);
        }

        qlksServiceRepository.delete(id);
    }

    @Override
    public void updateService(String id, UpdateServiceRequest updateServiceRequest) throws HotelManagerException {
        if (qlksServiceRepository.getById(id).isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVICE_NOT_EXISTED);
        }
        if (qlksServiceRepository.getByNameServiceAndPrice(updateServiceRequest.getNameService(), updateServiceRequest.getPrice()).isPresent()) {
            log.error("Name service existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVICE_ALREADY_EXISTED);
        }

        qlksServiceRepository.update(id, updateServiceRequest);
    }

    @Override
    public QLKSServiceEntity getDetailService(String id) throws HotelManagerException {
        Optional<QLKSServiceEntity> qlksServiceEntity = qlksServiceRepository.getById(id);
        if (qlksServiceEntity.isEmpty()) {
            log.error("idService not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_SERVICE_NOT_EXISTED);
        }

        return qlksServiceEntity.get();
    }
}
