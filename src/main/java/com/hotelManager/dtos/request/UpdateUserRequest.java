package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class UpdateUserRequest {

    private String email;

    private String nameEmployee;

    private String gender;

    private String identityCard;

    private String address;

    private String phoneNumber;
}
