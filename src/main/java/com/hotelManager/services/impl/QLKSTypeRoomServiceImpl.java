package com.hotelManager.services.impl;

import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.services.QLKSTypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QLKSTypeRoomServiceImpl implements QLKSTypeRoomService {

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;

    @Override
    public List<QLKSTypeRoomEntity> getAll() throws HotelManagerException {
        return qlksTypeRoomRepository.getAll();
    }
}
