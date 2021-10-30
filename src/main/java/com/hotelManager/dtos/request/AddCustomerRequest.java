package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class AddCustomerRequest {

    @NotBlank(message = "email is not empty or null")
    private String email;

    @NotBlank(message = "nameCustomer is not empty or null")
    private String nameCustomer;

    @NotBlank(message = "nationality is not empty or null")
    private String nationality;

    @NotBlank(message = "identityCard is not empty or null")
    private String identityCard;

    @NotBlank(message = "phoneNumber is not empty or null")
    private String phoneNumber;

}
