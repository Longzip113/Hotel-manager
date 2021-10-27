package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.StatusRoom;
import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.dtos.request.UpdateRoomRequest;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.QLKSRoomRepository;
import com.hotelManager.services.QLKSRoomService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_CHECK_ROOM;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_SERVER;

@Service
@Slf4j
public class QLKSRoomServiceImpl implements QLKSRoomService {

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Override
    public void save(AddRoomRequest addRoomRequest) throws HotelManagerException {
        QLKSRoomEntity qlksRoomEntity = QLKSRoomEntity.builder()
                .description(addRoomRequest.getDescription())
                .idTypeRoom(addRoomRequest.getIdTypeRoom())
                .nameRoom(addRoomRequest.getNameRoom())
                .status(StatusRoom.NOT_BOOKED_YET.getValue())
                .isDelete(Boolean.FALSE).build();

        qlksRoomRepository.save(qlksRoomEntity);
    }

    @Override
    public void deleteRoom(String idRoom) {
        qlksRoomRepository.delete(idRoom);
    }

    @Override
    public void update(UpdateRoomRequest roomRequest, String idRoom) throws HotelManagerException {
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
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_CHECK_ROOM);
        }

        return qlksRoomRepository.getByIdRoom(idRoom).get();
    }
}
