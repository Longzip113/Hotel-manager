package com.hotelManager.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class QLKSInfoBillResponse {

    private Long dayOfPayment;

    private Long dayCheckIn;

    private Long dayCheckOut;

    private Long roomRent;

    private Long serviceFee;

    private Long totalMoney;

    private Integer typeRegistration;

    private QLKSRegistrationResponse infoRegistration;

    private List<QLKSInfoRoomCheckOutResponse> infoRoom;

}
