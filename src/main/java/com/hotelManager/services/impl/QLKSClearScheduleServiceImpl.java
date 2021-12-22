package com.hotelManager.services.impl;

import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.entities.QLKSRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.repositories.QLKSEmployeeRepository;
import com.hotelManager.repositories.QLKSRoomRepository;
import com.hotelManager.services.QLKSClearScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QLKSClearScheduleServiceImpl implements QLKSClearScheduleService {

    @Autowired
    QLKSEmployeeRepository qlksEmployeeRepository;

    @Autowired
    QLKSRoomRepository qlksRoomRepository;

    @Override
    public void save(Long dayWork) throws HotelManagerException {
        List<QLKSEmployeeModel> employeeList = qlksEmployeeRepository.getAll();
        List<QLKSRoomEntity> roomList = qlksRoomRepository.getAll();
        Integer lenght = employeeList.size();

        for (QLKSRoomEntity item: roomList) {
            lenght --;
            item.setIdHousekeepingStaff(employeeList.get(lenght).getId());
            if (lenght == 0) {
                lenght = employeeList.size();
            }
        }

        qlksRoomRepository.updateBatch(roomList);
    }

}
