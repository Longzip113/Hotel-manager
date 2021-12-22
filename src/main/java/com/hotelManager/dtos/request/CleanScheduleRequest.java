package com.hotelManager.dtos.request;

import lombok.Data;

@Data
public class CleanScheduleRequest {

    private String idEmployee;

    private String idRoom;

    private String day;
}
