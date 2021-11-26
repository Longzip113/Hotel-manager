package com.hotelManager.dtos.request;

import lombok.Data;


@Data
public class LogCustomerRequest {

    private String idRegistration;

    private String idCustomer;

    private Integer type;

    private String idType;

    private Integer quantity;

    private String description;

    private Integer totalPrice;
}
