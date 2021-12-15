package com.hotelManager.dtos.responses;

import com.hotelManager.model.QLKSDelegationModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class QLKSInfoTurnOverResponse {

    private Long dayOfPayment;

    private String nameRoom;

    private Long dayCheckOut;

    private Long dayCheckIn;

    private Integer roomRent;

    private Long serviceFee;

    private Long totalPrice;

    private QLKSArrangenmentCustomerResponse customer;

    private String nameEmployee;

    private Integer bookingType;

    private QLKSDelegationModel delegation;
}
