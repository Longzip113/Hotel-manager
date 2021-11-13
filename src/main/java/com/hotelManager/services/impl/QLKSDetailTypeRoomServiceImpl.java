package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.DetailTypeRoomRequest;
import com.hotelManager.entities.QLKSDetailTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSDetailTypeRoomRepository;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.services.QLKSDetailTypeRoomService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_ID_NOT_EXISTED;
import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_TYPE_ROOM_NOT_EXISTED;

@Service
@Slf4j
public class QLKSDetailTypeRoomServiceImpl implements QLKSDetailTypeRoomService {

    @Autowired
    QLKSDetailTypeRoomRepository qlksDetailTypeRoomRepository;

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;


    @Override
    public void add(DetailTypeRoomRequest request) throws HotelManagerException {
        Optional<QLKSDetailTypeRoomEntity> qlksDetailTypeRoom = qlksDetailTypeRoomRepository
                .getByIdTypeRoomAndIdDetail(request.getIdTypeRoom(), request.getIdDetailType(), request.getTypeDetail());

        if (qlksDetailTypeRoom.isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        if (qlksDetailTypeRoom.isPresent()) {

            Integer quantity = request.getQuantity() + qlksDetailTypeRoom.get().getQuantity();
            request.setQuantity(quantity);
            qlksDetailTypeRoomRepository.update(qlksDetailTypeRoom.get().getId(), request);
        }  else {
            QLKSDetailTypeRoomEntity entity = QLKSDetailTypeRoomEntity.builder()
                    .idDetail(request.getIdDetailType())
                    .idType(request.getIdTypeRoom())
                    .quantity(request.getQuantity())
                    .typeDetail(request.getTypeDetail())
                    .isDelete(Boolean.FALSE)
                    .build();

            qlksDetailTypeRoomRepository.save(entity);
        }
    }

    @Override
    public void delete(String id) throws HotelManagerException {
        Optional<QLKSDetailTypeRoomEntity> entity = qlksDetailTypeRoomRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        qlksDetailTypeRoomRepository.delete(id);
    }

    @Override
    public void update(String id, DetailTypeRoomRequest request) throws HotelManagerException {

        if (qlksTypeRoomRepository.getById(request.getIdTypeRoom()).isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        Optional<QLKSDetailTypeRoomEntity> qlksDetailTypeRoom = qlksDetailTypeRoomRepository
                .getByIdTypeRoomAndIdDetail(request.getIdTypeRoom(), request.getIdDetailType(), request.getTypeDetail());

        if (qlksDetailTypeRoom.isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        request.setQuantity(request.getQuantity());

        qlksDetailTypeRoomRepository.update(qlksDetailTypeRoom.get().getId(), request);

    }
}
