package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.StatusRoom;
import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRoomRepository;
import com.hotelManager.services.QLKSRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
