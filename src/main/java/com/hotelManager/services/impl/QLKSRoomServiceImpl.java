package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.StatusRoom;
import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.dtos.request.UpdateRoomRequest;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.QLKSRoomRepository;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.services.QLKSRoomService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSRoomServiceImpl implements QLKSRoomService {

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;

    @Override
    public void save(AddRoomRequest addRoomRequest) throws HotelManagerException {

        if (qlksRoomRepository.getByNameRoom(addRoomRequest.getNameRoom()).isPresent()) {
            log.error("Name room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_ALREADY_EXISTED);
        }

        if (qlksTypeRoomRepository.getById(addRoomRequest.getIdTypeRoom()).isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        QLKSRoomEntity qlksRoomEntity = QLKSRoomEntity.builder()
                .description(addRoomRequest.getDescription())
                .idTypeRoom(addRoomRequest.getIdTypeRoom())
                .nameRoom(addRoomRequest.getNameRoom())
                .status(StatusRoom.NOT_BOOKED_YET.getValue())
                .isDelete(Boolean.FALSE).build();

        qlksRoomRepository.save(qlksRoomEntity);
    }

    @Override
    public void deleteRoom(String idRoom) throws HotelManagerException {

        if (qlksRoomRepository.getByIdRoom(idRoom).isEmpty()) {
            log.error("idRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        qlksRoomRepository.delete(idRoom);
    }

    @Override
    public void update(UpdateRoomRequest roomRequest, String idRoom) throws HotelManagerException {

        if (qlksRoomRepository.getByIdRoom(idRoom).isEmpty()) {
            log.error("idRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        if (qlksRoomRepository.getByNameRoom(roomRequest.getNameRoom()).isPresent()) {
            log.error("Name room existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_ALREADY_EXISTED);
        }
        if (StringUtils.isNotBlank(roomRequest.getIdTypeRoom())) {
            if (qlksTypeRoomRepository.getById(roomRequest.getIdTypeRoom()).isEmpty()) {
                log.error("idTypeRoom not existed !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
            }
        }

        qlksRoomRepository.update(roomRequest, idRoom);
    }

    @Override
    public List<QLKSRoomModel> getAll(String sortBy, String sortOrder) {
        return qlksRoomRepository.getAll(sortBy, sortOrder);
    }

    @Override
    public QLKSRoomModel getDetailRoom(String idRoom) throws HotelManagerException {

        if(qlksRoomRepository.getByIdRoom(idRoom).isEmpty()) {
            log.error("IdRoom does not exist !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_NOT_EXISTED);
        }

        return qlksRoomRepository.getByIdRoom(idRoom).get();
    }
}
