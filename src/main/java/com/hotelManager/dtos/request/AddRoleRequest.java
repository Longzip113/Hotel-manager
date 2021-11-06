package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class AddRoleRequest {

    @NotBlank(message = "description is not empty or null")
    private String nameRole;

    @NotBlank(message = "nameTypeRoom is not empty or null")
    private String code;
}
