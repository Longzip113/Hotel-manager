package com.hotelManager.dtos.responses;

import com.hotelManager.entities.QLKSCustomerEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Builder
public class QLKSInfoRoomCheckOutResponse {

    private String idRoom;

    private String nameRoom;

    private String idTypeRoom;

    private String nameTypeRoom;

    private List<QLKSCustomerEntity> customers;

    private List<QLKSInfoLogCheckOutResponse> logCustomers;
}
