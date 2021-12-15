package com.hotelManager.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ServiceRequest {

    @NotBlank(message = "nameService is not empty or null")
    private String nameService;

    private Integer price;

}
