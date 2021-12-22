package com.hotelManager.dtos.responses;

import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QLKSCleanScheduleResponse {

    QLKSRoomModel room;

    QLKSEmployeeModel employee;

    String dayWork;
}
