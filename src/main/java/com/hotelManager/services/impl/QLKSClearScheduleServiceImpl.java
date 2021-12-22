package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.StatusRoom;
import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.dtos.request.GetViewCleanScheduleRequest;
import com.hotelManager.dtos.responses.QLKSCleanScheduleResponse;
import com.hotelManager.dtos.responses.QLKSLogCleanScheduleResponse;
import com.hotelManager.entities.QLKSCleanScheduleEntity;
import com.hotelManager.entities.QLKSLogCleanRoomEntity;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.QLKSCleanScheduleRepository;
import com.hotelManager.repositories.QLKSEmployeeRepository;
import com.hotelManager.repositories.QLKSLogCleanScheduleRepository;
import com.hotelManager.repositories.QLKSRoomRepository;
import com.hotelManager.services.QLKSClearScheduleService;
import com.hotelManager.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QLKSClearScheduleServiceImpl implements QLKSClearScheduleService {

    @Autowired
    QLKSEmployeeRepository qlksEmployeeRepository;

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Autowired
    QLKSCleanScheduleRepository qlksCleanScheduleRepository;

    @Autowired
    QLKSLogCleanScheduleRepository qlksLogCleanScheduleRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save() throws HotelManagerException {
        List<QLKSEmployeeModel> employeeList = qlksEmployeeRepository.getAll();
        List<QLKSRoomEntity> roomList = qlksRoomRepository.getAll();
        List<QLKSCleanScheduleEntity> listCleanSchedule = new ArrayList<>();
        Integer lenght = employeeList.size();

        for (QLKSRoomEntity item: roomList) {
            lenght --;
            item.setIdHousekeepingStaff(employeeList.get(lenght).getId());
            QLKSCleanScheduleEntity scheduleEntity = QLKSCleanScheduleEntity.builder()
                    .dayWorking(java.time.LocalDate.now().toString())
                    .idRoom(item.getId())
                    .idEmployee(employeeList.get(lenght).getId()).build();

            listCleanSchedule.add(scheduleEntity);

            if (lenght == 0) {
                lenght = employeeList.size();
            }
        }

        qlksRoomRepository.updateBatch(roomList);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        qlksCleanScheduleRepository.batchSave(listCleanSchedule, session);
        session.getTransaction().commit();
    }

    @Override
    public List<QLKSCleanScheduleResponse> getAll(CleanScheduleRequest request) throws HotelManagerException {
        List<QLKSCleanScheduleEntity> entityList = qlksCleanScheduleRepository.getAll(request);
        List<QLKSCleanScheduleResponse> results = new ArrayList<>();

        entityList.stream().forEach(item -> {

            QLKSRoomModel room = qlksRoomRepository.getByIdRoom(item.getIdRoom()).get();
            QLKSEmployeeModel employee = qlksEmployeeRepository.getById(item.getIdEmployee()).get();

            QLKSCleanScheduleResponse result = QLKSCleanScheduleResponse.builder()
                    .employee(employee)
                    .room(room)
                    .dayWork(item.getDayWorking()).build();

            results.add(result);

        });

        return results;
    }

    @Override
    public List<QLKSLogCleanScheduleResponse> getAllLog(CleanScheduleRequest request) throws HotelManagerException {
        List<QLKSLogCleanRoomEntity> entityList = qlksLogCleanScheduleRepository.getAll(request);
        List<QLKSLogCleanScheduleResponse> results = new ArrayList<>();

        entityList.stream().forEach(item -> {

            QLKSRoomModel room = qlksRoomRepository.getByIdRoom(item.getIdRoom()).get();
            QLKSEmployeeModel employee = qlksEmployeeRepository.getById(item.getIdEmployee()).get();

            QLKSLogCleanScheduleResponse result = QLKSLogCleanScheduleResponse.builder()
                    .employee(employee)
                    .room(room)
                    .timeStart(item.getTimeStart())
                    .timeEnd(item.getTimeEnd())
                    .dayWork(item.getDayWorking()).build();

            results.add(result);
        });

        return results;
    }

    @Override
    public List<QLKSCleanScheduleResponse> getAllViewRoom(GetViewCleanScheduleRequest request) throws HotelManagerException {
        List<QLKSCleanScheduleEntity> entityList = qlksCleanScheduleRepository.getAllViewRoom(request);
        List<QLKSCleanScheduleResponse> results = new ArrayList<>();

        entityList.stream().forEach(item -> {

            QLKSRoomModel room = qlksRoomRepository.getByIdRoom(item.getIdRoom()).get();
            QLKSEmployeeModel employee = qlksEmployeeRepository.getById(item.getIdEmployee()).get();

            QLKSCleanScheduleResponse result = QLKSCleanScheduleResponse.builder()
                    .employee(employee)
                    .room(room)
                    .dayWork(item.getDayWorking()).build();

            results.add(result);

        });

        return results;
    }

    @Override
    public void checkInClean(String idRoom, String idEmployee) throws HotelManagerException {
        qlksRoomRepository.updateStatus(Arrays.asList(idRoom), StatusRoom.MAINTENANCE.getValue());
        QLKSLogCleanRoomEntity qlksLogCleanRoomEntity = QLKSLogCleanRoomEntity.builder()
                .idRoom(idRoom)
                .idEmployee(idEmployee)
                .timeStart(DateTimeUtils.getCurrentTime())
                .dayWorking(java.time.LocalDate.now().toString()).build();

        qlksLogCleanScheduleRepository.save(qlksLogCleanRoomEntity);
    }

    @Override
    public void checkOutClean(String idRoom, String idEmployee) throws HotelManagerException {
        qlksRoomRepository.updateStatus(Arrays.asList(idRoom), StatusRoom.NOT_BOOKED_YET.getValue());
        Optional<QLKSLogCleanRoomEntity> qlksLogCleanRoomEntity = qlksLogCleanScheduleRepository.getByRoomAndEmployee(idRoom
                , idEmployee);

        qlksLogCleanRoomEntity.get().setTimeEnd(DateTimeUtils.getCurrentTime());
        qlksLogCleanScheduleRepository.update(qlksLogCleanRoomEntity.get());
    }

}
