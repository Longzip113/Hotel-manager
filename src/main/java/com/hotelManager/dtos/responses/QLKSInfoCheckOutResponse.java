package com.hotelManager.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Builder
public class QLKSInfoCheckOutResponse {

    private QLKSRegistrationResponse infoRegistration;

    private List<QLKSInfoRoomCheckOutResponse> infoRoom;

    private Long totalPrice;

}
