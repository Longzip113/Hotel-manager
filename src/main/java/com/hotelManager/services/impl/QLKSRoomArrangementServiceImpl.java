package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.BookingType;
import com.hotelManager.constants.enums.TypeRegister;
import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.entities.QLKSRoomArrangementEntity;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.repositories.QLKSRegistrationFormRepository;
import com.hotelManager.repositories.QLKSRoomArrangementRepository;
import com.hotelManager.services.QLKSRoomArrangementService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.ERROR_ID_NOT_EXISTED;

@Service
@Slf4j
public class QLKSRoomArrangementServiceImpl implements QLKSRoomArrangementService {

    @Autowired
    QLKSRegistrationFormRepository qlksRegistrationFormRepository;

    @Autowired
    QLKSRoomArrangementRepository qlksRoomArrangementRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void checkIn(String id) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }
        QLKSRegistrationFormEntity qlksRegistrationFormEntity = entity.get();
        List<String> listIdRooms = new ArrayList<>();
        List<String> listIdCustomer = new ArrayList<>();
        if (StringUtils.isNotBlank(qlksRegistrationFormEntity.getIdCustomer())) {
            listIdCustomer = List.of(qlksRegistrationFormEntity.getIdCustomer().split("/"));
        }

        if (StringUtils.isNotBlank(qlksRegistrationFormEntity.getIdRoom())) {
            listIdRooms = List.of(qlksRegistrationFormEntity.getIdRoom().split("/"));
        }

        List<QLKSRoomArrangementEntity> entities = arrangementRoom(qlksRegistrationFormEntity, listIdRooms, listIdCustomer);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        qlksRoomArrangementRepository.batchSave(entities, session);
        session.getTransaction().commit();
        qlksRegistrationFormEntity.setStatus(TypeRegister.CHECK_IN.getValue());
        qlksRegistrationFormRepository.update(qlksRegistrationFormEntity);
    }

    /**
     * hàm xếp phòng cho khách hàng
     *
     * @param qlksRegistrationFormEntity
     * @param listIdRooms
     * @param listIdCustomer
     * @return
     */
    private List<QLKSRoomArrangementEntity> arrangementRoom (QLKSRegistrationFormEntity qlksRegistrationFormEntity,
                                                             List<String> listIdRooms, List<String> listIdCustomer) {
        List<QLKSRoomArrangementEntity> entities = new ArrayList<>();
        Integer indexIdCustomer = 0;
        Integer sizeCustomer = listIdCustomer.size();
        String id = qlksRegistrationFormEntity.getId();

        if(StringUtils.isNotBlank(qlksRegistrationFormEntity.getIdDelegation())) {
            for (int i = 0; i < listIdRooms.size(); i++) {
                String idCustomer = "";
                if (sizeCustomer > indexIdCustomer) {
                    idCustomer = listIdCustomer.get(indexIdCustomer) + "/" + listIdCustomer.get(indexIdCustomer + 1);
                } else if(sizeCustomer == indexIdCustomer - 1) {
                    idCustomer = listIdCustomer.get(indexIdCustomer - 1);
                }

                QLKSRoomArrangementEntity roomArrangementEntity = QLKSRoomArrangementEntity.builder()
                        .idRoom(listIdRooms.get(i))
                        .isDelete(Boolean.FALSE)
                        .idRegistrationForm(id)
                        .idCustomer(idCustomer)
                        .numberOfChildren(0)
                        .build();

                entities.add(roomArrangementEntity);

                indexIdCustomer += 2;
            }
        } else {
            QLKSRoomArrangementEntity roomArrangementEntity = QLKSRoomArrangementEntity.builder()
                    .idRoom(qlksRegistrationFormEntity.getIdRoom())
                    .isDelete(Boolean.FALSE)
                    .idRegistrationForm(id)
                    .idCustomer(qlksRegistrationFormEntity.getIdCustomer())
                    .numberOfChildren(qlksRegistrationFormEntity.getNumberOfChild())
                    .build();

            entities.add(roomArrangementEntity);
        }

        return entities;
    }

    @Override
    public void checkOut(String id) throws HotelManagerException {

    }
}
