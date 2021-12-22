package com.hotelManager.dtos.responses;

import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QLKSLogCleanScheduleResponse {

    QLKSRoomModel room;

    QLKSEmployeeModel employee;

    Long timeStart;

    Long timeEnd;

    String dayWork;
}
