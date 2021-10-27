package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.AddTypeRoomRequest;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.services.QLKSTypeRoomService;
import com.hotelManager.utils.GsonHelper;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_DELETE_TYPE_ROOM;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

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

        QLKSTypeRoomEntity qlksTypeRoomEntity = QLKSTypeRoomEntity.builder()
                .nameTypeRoom(addTypeRoomRequest.getNameTypeRoom())
                .price(addTypeRoomRequest.getPrice())
                .build();

        qlksTypeRoomRepository.save(qlksTypeRoomEntity);
    }

    @Override
    public void deleteTypeRoom(String id) throws HotelManagerException {
        if (qlksTypeRoomRepository.countRoomByTypeRoom(id) > 0) {
            log.error("Save TypeRoom failed Id: [{}]", id);
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_DELETE_TYPE_ROOM);
        }

        qlksTypeRoomRepository.delete(id);
    }
}
