package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Data
public class RegistrationRequest {

    private Long bookingDate;

    private Long checkInDate;

    private Long checkOutDate;

    private String note;

    private List<String> idRoom;

    private String idEmployee;

    private String idDelegation;

    private Integer numberOfChild;

    private Integer intoMoney;

    private Integer type;
}
