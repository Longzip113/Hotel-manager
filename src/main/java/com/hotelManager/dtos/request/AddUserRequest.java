package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class AddUserRequest {
    @NotBlank(message = "email is not empty or null")
    private String email;

    @NotBlank(message = "nameEmployee is not empty or null")
    private String nameEmployee;

    @NotBlank(message = "gender is not empty or null")
    private String gender;

    @NotBlank(message = "identityCard is not empty or null")
    private String identityCard;

    @NotBlank(message = "address is not empty or null")
    private String address;

    @NotBlank(message = "phoneNumber is not empty or null")
    private String phoneNumber;
}
