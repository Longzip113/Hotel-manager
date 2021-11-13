package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class ChangePasswordRequest {

    @NotBlank(message = "Email is not empty or null")
    private String email;

    @NotBlank(message = "passwordOld is not empty or null")
    private String passwordOld;

    @NotBlank(message = "passwordNew is not empty or null")
    private String passwordNew;

}
