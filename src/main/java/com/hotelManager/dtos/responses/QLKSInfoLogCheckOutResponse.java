package com.hotelManager.dtos.responses;

import com.hotelManager.entities.QLKSCustomerEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class QLKSInfoLogCheckOutResponse {

    private String name;

    private Integer type;

    private Integer quantity;

    private Long time;

    private String idType;

    private String description;

    private QLKSCustomerEntity customer;

    private Integer totalPrice;
}
