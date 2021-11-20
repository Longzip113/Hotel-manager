package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class ServiceRequest {

    @NotBlank(message = "nameService is not empty or null")
    private String nameService;

    private Integer price;

}