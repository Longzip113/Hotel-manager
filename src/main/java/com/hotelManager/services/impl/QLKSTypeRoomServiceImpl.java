package com.hotelManager.services.impl;

import com.hotelManager.dtos.request.TypeRoomRequest;
import com.hotelManager.dtos.responses.QLKSTypeRoomReponse;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSDetailTypeRoomRepository;
import com.hotelManager.repositories.QLKSTypeRoomRepository;
import com.hotelManager.services.QLKSTypeRoomService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSTypeRoomServiceImpl implements QLKSTypeRoomService {

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;

    @Autowired
    QLKSDetailTypeRoomRepository qlksDetailTypeRoomRepository;

    @Override
    public List<QLKSTypeRoomReponse> getAll() throws HotelManagerException {

        List<QLKSTypeRoomEntity> qlksTypeRoomEntities = qlksTypeRoomRepository.getAll();
        List<QLKSTypeRoomReponse> results = new ArrayList<>();
        qlksTypeRoomEntities.stream().forEach(item -> {
            QLKSTypeRoomReponse result = QLKSTypeRoomReponse.builder()
                    .description(item.getDescription())
                    .nameTypeRoom(item.getNameTypeRoom())
                    .price(item.getPrice())
                    .details(qlksDetailTypeRoomRepository.getByIdTypeRoom(item.getId()))
                    .id(item.getId()).build();

            results.add(result);

        });

        return results;
    }

    @Override
    public void addTypeRoom(TypeRoomRequest typeRoomRequest) throws HotelManagerException {

        if (qlksTypeRoomRepository.getByNameType(typeRoomRequest.getNameTypeRoom()).isPresent()) {
            log.error("Name type existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_ALREADY_EXISTED);
        }

        QLKSTypeRoomEntity qlksTypeRoomEntity = QLKSTypeRoomEntity.builder()
                .nameTypeRoom(typeRoomRequest.getNameTypeRoom())
                .price(typeRoomRequest.getPrice())
                .description(typeRoomRequest.getDescription())
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
    public void updateTypeRoom(String id, TypeRoomRequest typeRoomRequest) throws HotelManagerException {
        if (qlksTypeRoomRepository.getById(id).isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }
        if(StringUtils.isNotBlank(typeRoomRequest.getNameTypeRoom())) {
            if (qlksTypeRoomRepository.getByNameTypeAndPrice(typeRoomRequest.getNameTypeRoom(), typeRoomRequest.getPrice(), typeRoomRequest.getDescription()).isPresent()) {
                log.error("Name type existed !");
                HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_ALREADY_EXISTED);
            }
        }

        qlksTypeRoomRepository.update(id, typeRoomRequest);
    }

    @Override
    public QLKSTypeRoomReponse getDetailTypeRoom(String id) throws HotelManagerException {
        Optional<QLKSTypeRoomEntity> qlksTypeRoomEntity = qlksTypeRoomRepository.getById(id);
        if (qlksTypeRoomEntity.isEmpty()) {
            log.error("idTypeRoom not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_TYPE_ROOM_NOT_EXISTED);
        }

        return QLKSTypeRoomReponse.builder()
                .description(qlksTypeRoomEntity.get().getDescription())
                .nameTypeRoom(qlksTypeRoomEntity.get().getNameTypeRoom())
                .price(qlksTypeRoomEntity.get().getPrice())
                .details(qlksDetailTypeRoomRepository.getByIdTypeRoom(qlksTypeRoomEntity.get().getId()))
                .id(qlksTypeRoomEntity.get().getId()).build();
    }
}
