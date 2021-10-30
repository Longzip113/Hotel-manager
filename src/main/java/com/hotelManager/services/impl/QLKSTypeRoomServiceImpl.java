package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.AddTypeRoomRequest;
import com.hotelManager.dtos.request.UpdateTypeRoomRequest;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
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
public class QLKSTypeRoomServiceImpl implements QLKSTypeRoomService {

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;

    @Override
    public List<QLKSTypeRoomEntity> getAll() throws HotelManagerException {
        return qlksTypeRoomRepository.getAll();
    }

    @Override
    public void addTypeRoom(AddTypeRoomRequest addTypeRoomRequest) throws HotelManagerException {

        if (qlksTypeRoomRepository.getByNameType(addTypeRoomRequest.getNameTypeRoom()).isPresent()) {
            log.error("Name type existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_ALREADY_EXISTED);
        }

        QLKSTypeRoomEntity qlksTypeRoomEntity = QLKSTypeRoomEntity.builder()
                .nameTypeRoom(addTypeRoomRequest.getNameTypeRoom())
                .price(addTypeRoomRequest.getPrice())
                .isDelete(Boolean.FALSE)
                .build();

        qlksTypeRoomRepository.save(qlksTypeRoomEntity);
    }

    @Override
    public void deleteTypeRoom(String id) throws HotelManagerException {

        if (qlksTypeRoomRepository.getById(id).isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        if (qlksTypeRoomRepository.countRoomByTypeRoom(id) > 0) {
            log.error("Delete TypeRoom failed Id: [{}]", id);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_DELETE_TYPE_ROOM);
        }

        qlksTypeRoomRepository.delete(id);
    }

    @Override
    public void updateTypeRoom(String id, UpdateTypeRoomRequest updateTypeRoomRequest) throws HotelManagerException {
        if (qlksTypeRoomRepository.getById(id).isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }
        if(StringUtils.isNotBlank(updateTypeRoomRequest.getNameTypeRoom())) {
            if (qlksTypeRoomRepository.getByNameTypeAndPrice(updateTypeRoomRequest.getNameTypeRoom(), updateTypeRoomRequest.getPrice()).isPresent()) {
                log.error("Name type existed !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_ALREADY_EXISTED);
            }
        }

        qlksTypeRoomRepository.update(id, updateTypeRoomRequest);
    }

    @Override
    public QLKSTypeRoomEntity getDetailTypeRoom(String id) throws HotelManagerException {
        Optional<QLKSTypeRoomEntity> qlksTypeRoomEntity = qlksTypeRoomRepository.getById(id);
        if (qlksTypeRoomEntity.isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        return qlksTypeRoomEntity.get();
    }
}
