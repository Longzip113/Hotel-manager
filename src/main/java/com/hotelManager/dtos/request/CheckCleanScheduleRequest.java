package com.hotelManager.dtos.request;

import lombok.Data;

@Data
public class CheckCleanScheduleRequest {

    private String idEmployee;

    private String idRoom;
}
