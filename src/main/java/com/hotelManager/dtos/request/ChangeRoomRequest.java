package com.hotelManager.dtos.request;

import lombok.Data;


@Data
public class ChangeRoomRequest {

    private String idRegistration;

    private String idCustomer;

    private String idRoomOld;

    private String idRoomNew;
}
