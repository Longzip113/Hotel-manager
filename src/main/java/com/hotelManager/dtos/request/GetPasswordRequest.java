package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class GetPasswordRequest {

    @NotBlank(message = "Verification is not empty or null")
    private String verification;

    @NotBlank(message = "password is not empty or null")
    private String password;

}
