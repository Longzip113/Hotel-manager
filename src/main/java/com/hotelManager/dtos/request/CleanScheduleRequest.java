package com.hotelManager.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CleanScheduleRequest {

    private String idEmployee;

    private String idRoom;

    private String day;
}
