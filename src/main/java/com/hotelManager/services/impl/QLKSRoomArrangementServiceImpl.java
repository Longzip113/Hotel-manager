package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.StatusRoom;
import com.hotelManager.constants.enums.TypeDetailofTypeRoom;
import com.hotelManager.constants.enums.TypeRegister;
import com.hotelManager.dtos.responses.*;
import com.hotelManager.entities.*;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.*;
import com.hotelManager.services.QLKSCustomerService;
import com.hotelManager.services.QLKSRegistrationFormService;
import com.hotelManager.services.QLKSRoomArrangementService;
import com.hotelManager.services.QLKSServiceService;
import com.hotelManager.utils.DateTimeUtils;
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
import java.util.concurrent.atomic.AtomicReference;

import static com.hotelManager.constants.enums.HotelManagerResponseCode.*;

@Service
@Slf4j
public class QLKSRoomArrangementServiceImpl implements QLKSRoomArrangementService {

    @Autowired
    QLKSRegistrationFormRepository qlksRegistrationFormRepository;

    @Autowired
    QLKSRoomArrangementRepository qlksRoomArrangementRepository;

    @Autowired
    QLKSLogCustomerRepository qlksLogCustomerRepository;

    @Autowired
    QLKSBillRepository qlksBillRepository;

    @Autowired
    QLKSRegistrationFormService qlksRegistrationFormService;

    @Autowired
    QLKSServiceRepository qlksServiceRepository;

    @Autowired
    QLKSHotelDeviceRepository qlksHotelDeviceRepository;

    @Autowired
    QLKSCustomerRepository qlksCustomerRepository;

    @Autowired
    QLKSCustomerService qlksCustomerService;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    Long totalPrice = 0l;

    @Override
    public void checkIn(String id) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (entity.get().getStatus() != TypeRegister.BOOK_ROOM.getValue()) {
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_CHECK_IN);
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
        Integer sizeCustomer = listIdCustomer.size() - 1;
        String id = qlksRegistrationFormEntity.getId();

        if(StringUtils.isNotBlank(qlksRegistrationFormEntity.getIdDelegation())) {
            for (int i = 0; i < listIdRooms.size(); i++) {
                String idCustomer = "";
                if (sizeCustomer > indexIdCustomer) {
                    idCustomer = listIdCustomer.get(indexIdCustomer) + "/" + listIdCustomer.get(indexIdCustomer + 1);
                } else if(sizeCustomer == indexIdCustomer) {
                    idCustomer = listIdCustomer.get(indexIdCustomer);
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
        Optional<QLKSRegistrationFormEntity> registrationEntity = qlksRegistrationFormRepository.getById(id);
        if (registrationEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (registrationEntity.get().getStatus() != TypeRegister.CHECK_IN.getValue()) {
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_CHECK_OUT);
        }

        QLKSRegistrationFormEntity qlksRegistrationFormEntity = registrationEntity.get();
        QLKSInfoCheckOutResponse infoCheckOutResponse = getInfoCheckOut(id);

        AtomicReference<Long> serviceFee = new AtomicReference<>(0l);
        infoCheckOutResponse.getInfoRoom().forEach(item -> {
            item.getLogCustomers().forEach(itemLog -> {
                serviceFee.updateAndGet(v -> v + itemLog.getTotalPrice());
            });
        });

        QLKSBillEntity billEntity = QLKSBillEntity.builder()
                .idRoom(registrationEntity.get().getIdRoom())
                .idRegistrationForm(registrationEntity.get().getId())
                .idCustomer(registrationEntity.get().getIdCustomer())
                .dayOfPayment(DateTimeUtils.getCurrentTime())
                .roomRent(registrationEntity.get().getIntoMoney())
                .serviceFee(serviceFee.get())
                .isDelete(Boolean.FALSE).build();

        billEntity.setTotalMoney(billEntity.getRoomRent() + billEntity.getServiceFee());

        qlksBillRepository.save(billEntity);

        qlksRegistrationFormEntity.setStatus(TypeRegister.CHECK_OUT.getValue());
        qlksRegistrationFormRepository.update(qlksRegistrationFormEntity);

        //update status room
        List<String> listIdRooms = List.of(qlksRegistrationFormEntity.getIdRoom().split("/"));
        qlksRoomRepository.updateStatus(listIdRooms, StatusRoom.CLEANING_UP.getValue());

    }

    @Override
    public void cancel(String id) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (entity.get().getStatus() != TypeRegister.BOOK_ROOM.getValue()) {
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_CHECK_IN);
        }

        QLKSRegistrationFormEntity qlksRegistrationFormEntity = entity.get();

        qlksRegistrationFormEntity.setStatus(TypeRegister.CANCEL.getValue());
        qlksRegistrationFormRepository.update(qlksRegistrationFormEntity);
    }

    @Override
    public QLKSInfoCheckOutResponse getInfoCheckOut(String id) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> entity = qlksRegistrationFormRepository.getById(id);
        totalPrice += entity.get().getIntoMoney();
        if (entity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (entity.get().getStatus() != TypeRegister.CHECK_IN.getValue()) {
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_CHECK_OUT);
        }

        QLKSRegistrationResponse infoRegistration = qlksRegistrationFormService.getDetail(id);
        List<QLKSInfoRoomCheckOutResponse> infoRooms = new ArrayList<>();
        infoRegistration.getRooms().parallelStream().forEach(item -> {
            try {
                infoRooms.add(setInfoRoom(item, id));
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
        });


        return QLKSInfoCheckOutResponse.builder()
                .infoRegistration(infoRegistration)
                .infoRoom(infoRooms)
                .totalPrice(totalPrice).build();
    }

    /**
     * set info room
     *
     * @param itemRoom
     * @param idRegistration
     * @return
     * @throws HotelManagerException
     */
    private QLKSInfoRoomCheckOutResponse setInfoRoom(QLKSRoomModel itemRoom, String idRegistration) throws HotelManagerException {
        List<QLKSLogCustomerEntity> entities = qlksLogCustomerRepository.getByRegistrationAndRoom(idRegistration, itemRoom.getId());
        List<QLKSInfoLogCheckOutResponse> infoLogs = new ArrayList<>();
        Optional<QLKSRoomArrangementEntity> arrangementEntity = qlksRoomArrangementRepository.getByIdRegisterAndRoom(
                idRegistration, itemRoom.getId()
        );

        List<String> listIdCustomer = List.of(arrangementEntity.get().getIdCustomer().split("/"));
        Integer size = listIdCustomer.size();
        List<QLKSCustomerEntity> customerEntityList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            QLKSCustomerEntity customerEntity = qlksCustomerService.getDetail(listIdCustomer.get(i));
            customerEntityList.add(customerEntity);
        }

        entities.parallelStream().forEach(item -> {
            try {
                infoLogs.add(setInfoLog(item));
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
        });

        return QLKSInfoRoomCheckOutResponse.builder()
                .idRoom(itemRoom.getId())
                .idTypeRoom(itemRoom.getIdTypeRoom())
                .nameRoom(itemRoom.getNameRoom())
                .nameTypeRoom(itemRoom.getNameTypeRoom())
                .customers(customerEntityList)
                .logCustomers(infoLogs).build();
    }

    /**
     * set info log
     *
     * @param itemEntity
     * @return
     * @throws HotelManagerException
     */
    private QLKSInfoLogCheckOutResponse setInfoLog(QLKSLogCustomerEntity itemEntity) throws HotelManagerException {

        QLKSInfoLogCheckOutResponse result = QLKSInfoLogCheckOutResponse.builder()
                .idType(itemEntity.getIdType())
                .type(itemEntity.getType())
                .quantity(itemEntity.getQuantity())
                .description(itemEntity.getDescription())
                .time(itemEntity.getLogTime())
                .totalPrice(itemEntity.getTotalPrice())
                .build();
        totalPrice += itemEntity.getTotalPrice();
        Optional<QLKSCustomerEntity> customerEntity = qlksCustomerRepository.getById(itemEntity
                .getIdCustomer());

        if(customerEntity.isPresent()) {
            result.setCustomer(customerEntity.get());
        }

        if (itemEntity.getType() == TypeDetailofTypeRoom.SERVICE.getValue()) {
            Optional<QLKSServiceEntity> itemService = qlksServiceRepository.getById(itemEntity.getIdType());
            if (itemService.isPresent()) {
                result.setName(itemService.get().getNameService());
            }
        } else {
            Optional<QLKSHotelDeviceEntity> itemDevice = qlksHotelDeviceRepository.getById(itemEntity.getIdType());
            if (itemDevice.isPresent()) {
                result.setName(itemDevice.get().getNameHotelDevice());
            }
        }

        return result;
    }
}
