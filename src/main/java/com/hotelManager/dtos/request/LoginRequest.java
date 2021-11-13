package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class LoginRequest {

    @NotBlank(message = "Email is not empty or null")
    private String email;

    @NotBlank(message = "passWord is not empty or null")
    private String passWord;

}
