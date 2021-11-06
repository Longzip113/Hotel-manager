package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.AddHotelDeviceRequest;
import com.hotelManager.dtos.request.UpdateHotelDeviceRequest;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSHotelDeviceRepository;
import com.hotelManager.services.QLKSHotelDeviceService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSHotelDeviceServiceImpl implements QLKSHotelDeviceService {

    @Autowired
    QLKSHotelDeviceRepository qlksHotelDeviceRepository;


    @Override
    public List<QLKSHotelDeviceEntity> getAll() throws HotelManagerException {
        return qlksHotelDeviceRepository.getAll();
    }

    @Override
    public void add(AddHotelDeviceRequest addHotelDeviceRequest) throws HotelManagerException {

        Optional<QLKSHotelDeviceEntity> qlksHotelDeviceEntity = qlksHotelDeviceRepository.getByNameAndPrice(addHotelDeviceRequest.getNameHotelDevice()
                , addHotelDeviceRequest.getPrice());

        if (qlksHotelDeviceEntity.isPresent()) {
            UpdateHotelDeviceRequest update = UpdateHotelDeviceRequest.builder()
                    .nameHotelDevice(addHotelDeviceRequest.getNameHotelDevice())
                    .price(addHotelDeviceRequest.getPrice())
                    .quantity(addHotelDeviceRequest.getQuantity() + qlksHotelDeviceEntity.get().getQuantity())
                    .build();
            qlksHotelDeviceRepository.update(qlksHotelDeviceEntity.get().getId(), update);
        } else if (qlksHotelDeviceRepository.getByName(addHotelDeviceRequest.getNameHotelDevice()).isPresent()) {

            log.error("Name device existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_DEVICE_ALREADY_EXISTED);
        } else {
            QLKSHotelDeviceEntity entity = QLKSHotelDeviceEntity.builder()
                    .nameHotelDevice(addHotelDeviceRequest.getNameHotelDevice())
                    .price(addHotelDeviceRequest.getPrice())
                    .quantity(addHotelDeviceRequest.getQuantity())
                    .isDelete(Boolean.FALSE)
                    .status(Boolean.TRUE)
                    .build();

            qlksHotelDeviceRepository.save(entity);
        }

    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Optional<QLKSHotelDeviceEntity> qlksHotelDeviceEntity = qlksHotelDeviceRepository.getById(id);
        if (qlksHotelDeviceEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

       if (qlksHotelDeviceRepository.countRoomByHotelDevice(id) > 0) {
            log.error("Delete device failed Id: [{}]", id);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_DELETE_DEVICE);
       }


        qlksHotelDeviceRepository.delete(id);
    }

    @Override
    public void update(String id, UpdateHotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException {
        Optional<QLKSHotelDeviceEntity> qlksHotelDeviceEntity = qlksHotelDeviceRepository.getById(id);
        if (qlksHotelDeviceEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        qlksHotelDeviceRepository.update(id, updateHotelDeviceRequest);
    }

    @Override
    public QLKSHotelDeviceEntity getDetail(String id) throws HotelManagerException {
        Optional<QLKSHotelDeviceEntity> qlksHotelDeviceEntity = qlksHotelDeviceRepository.getById(id);
        if (qlksHotelDeviceEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        return qlksHotelDeviceEntity.get();
    }
}
