package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.BookingType;
import com.hotelManager.constants.enums.TypeDetailofTypeRoom;
import com.hotelManager.constants.enums.TypeRegister;
import com.hotelManager.dtos.request.TurnOverRequest;
import com.hotelManager.dtos.responses.*;
import com.hotelManager.entities.*;
import com.hotelManager.exceptions.DatabaseException;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.*;
import com.hotelManager.services.QLKSBillService;
import com.hotelManager.services.QLKSCustomerService;
import com.hotelManager.services.QLKSRegistrationFormService;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
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
public class QLKSBillServiceImpl implements QLKSBillService {

    @Autowired
    QLKSRegistrationFormRepository qlksRegistrationFormRepository;

    @Autowired
    QLKSTypeRoomRepository qlksTypeRoomRepository;

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
    QLKSRoomArrangementRepository qlksRoomArrangementRepository;

    @Autowired
    QLKSCustomerService qlksCustomerService;

    @Autowired
    QLKSCustomerRepository qlksCustomerRepository;

    Long totalPrice = 0l;


    @Override
    public List<QLKSInfoBillResponse> getAll() throws HotelManagerException {

        List<QLKSBillEntity> listBill = qlksBillRepository.getAll();
        List<QLKSInfoBillResponse> results = new ArrayList<>();
        listBill.forEach(item -> {
            try {
                results.add(setInfoBill(item));
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
        });

        return results;
    }

    @Override
    public List<QLKSInfoTurnOverResponse> getTurnOver(TurnOverRequest turnOverRequest) throws HotelManagerException {
        List<QLKSBillEntity> listBill;
        if (turnOverRequest.getDayStart() != null) {
            listBill = qlksBillRepository.getAllByDay(turnOverRequest.getDayStart(),
                    turnOverRequest.getDayEnd());
        } else {
            listBill = qlksBillRepository.getAll();
        }

        List<QLKSInfoTurnOverResponse> results = new ArrayList<>();

        listBill.forEach(item -> {
            try {
                QLKSRegistrationResponse infoRegistration = qlksRegistrationFormService.getDetail(item.getIdRegistrationForm());
                totalPrice += infoRegistration.getIntoMoney();

                if (infoRegistration.getStatus() != TypeRegister.CHECK_OUT.getValue()) {
                    HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_CHECK_OUT);
                }

                infoRegistration.getRooms().forEach(itemRoom -> {
                    AtomicReference<Long> serviceFee = new AtomicReference<>(0l);
                    try {
                        List<QLKSLogCustomerEntity> logCustomerEntities = qlksLogCustomerRepository.getByRegistrationAndRoom(infoRegistration.getIdRegistration(),
                                itemRoom.getId());

                        logCustomerEntities.parallelStream().forEach(itemLog -> {
                            serviceFee.updateAndGet(v -> v + itemLog.getTotalPrice());
                        });

                        Optional<QLKSTypeRoomEntity> typeRoomEntity = qlksTypeRoomRepository.getById(itemRoom.getIdTypeRoom());

                        QLKSInfoTurnOverResponse itemResult = QLKSInfoTurnOverResponse.builder()
                                .dayCheckIn(infoRegistration.getCheckInDate())
                                .dayCheckOut(infoRegistration.getCheckOutDate())
                                .dayOfPayment(item.getDayOfPayment())
                                .serviceFee(serviceFee.get())
                                .idTypeRoom(itemRoom.getIdTypeRoom())
                                .roomRent(typeRoomEntity.get().getPrice())
                                .totalPrice(serviceFee.get() + typeRoomEntity.get().getPrice())
                                .bookingType(infoRegistration.getType())
                                .nameRoom(itemRoom.getNameRoom())
                                .customer(itemRoom.getInfoCustomerBooking())
                                .nameEmployee(itemRoom.getNameEmployee()).build();

                        if (infoRegistration.getType() == BookingType.DELEGATION.getValue()) {
                            itemResult.setDelegation(infoRegistration.getDelegation());
                        }

                        results.add(itemResult);
                    } catch (HotelManagerException e) {
                        e.printStackTrace();
                    }
                });

            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
        });

        return results;
    }

    @Override
    public QLKSInfoBillResponse getDetail(String id) throws HotelManagerException {
        Optional<QLKSBillEntity> qlksBillEntity = qlksBillRepository.getOne(id);
        if (qlksBillEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        return setInfoBill(qlksBillEntity.get());
    }


    private QLKSInfoBillResponse setInfoBill(QLKSBillEntity itemBill) throws HotelManagerException {
        Optional<QLKSRegistrationFormEntity> registrationEntity = qlksRegistrationFormRepository.getById(itemBill.getIdRegistrationForm());
        if (registrationEntity.isEmpty()) {
            log.error("id not existed !");
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ID_NOT_EXISTED);
        }

        if (registrationEntity.get().getStatus() != TypeRegister.CHECK_OUT.getValue()) {
            HotelManagerUtils.throwException(DatabaseException.class, ERROR_ROOM_CHECK_OUT);
        }
        QLKSRegistrationResponse infoRegistration = qlksRegistrationFormService.getDetail(registrationEntity.get().getId());
        List<QLKSInfoRoomCheckOutResponse> infoRooms = new ArrayList<>();
        infoRegistration.getRooms().parallelStream().forEach(item -> {
            try {
                infoRooms.add(setInfoRoom(item, infoRegistration.getIdRegistration()));
            } catch (HotelManagerException e) {
                e.printStackTrace();
            }
        });

        return QLKSInfoBillResponse.builder()
                .typeRegistration(registrationEntity.get().getType())
                .serviceFee(itemBill.getServiceFee())
                .roomRent(itemBill.getRoomRent())
                .totalMoney(itemBill.getTotalMoney())
                .dayOfPayment(itemBill.getDayOfPayment())
                .dayCheckIn(registrationEntity.get().getCheckInDate())
                .dayCheckOut(registrationEntity.get().getCheckOutDate())
                .infoRoom(infoRooms)
                .infoRegistration(infoRegistration).build();
    }

    private QLKSInfoRoomCheckOutResponse setInfoRoom(QLKSRoomModel itemRoom, String idRegistration) throws HotelManagerException {
        List<QLKSLogCustomerEntity> entities = qlksLogCustomerRepository.getByRegistrationAndRoom(idRegistration, itemRoom.getId());
        Optional<QLKSRoomArrangementEntity> customerByRoom = qlksRoomArrangementRepository.getByIdRegisterAndRoom(idRegistration
        , itemRoom.getId());

        List<String> listIdCustomer = List.of(customerByRoom.get().getIdCustomer().split("/"));
        Integer size = listIdCustomer.size();
        List<QLKSCustomerEntity> customerEntityList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            QLKSCustomerEntity customerEntity = qlksCustomerService.getDetail(listIdCustomer.get(i));
            customerEntityList.add(customerEntity);
        }

        List<QLKSInfoLogCheckOutResponse> infoLogs = new ArrayList<>();
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

        Optional<QLKSCustomerEntity> customerEntity = qlksCustomerRepository.getById(itemEntity.getIdCustomer());

        QLKSInfoLogCheckOutResponse result = QLKSInfoLogCheckOutResponse.builder()
                .idType(itemEntity.getIdType())
                .type(itemEntity.getType())
                .quantity(itemEntity.getQuantity())
                .description(itemEntity.getDescription())
                .time(itemEntity.getLogTime())
                .totalPrice(itemEntity.getTotalPrice())
                .customer(customerEntity.get())
                .build();
        totalPrice += itemEntity.getTotalPrice();

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
