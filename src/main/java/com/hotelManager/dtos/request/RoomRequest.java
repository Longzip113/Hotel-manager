package com.hotelManager.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoomRequest {

    @NotBlank(message = "nameRoom is not empty or null")
    private String nameRoom;

    @NotBlank(message = "description is not empty or null")
    private String description;

    @NotBlank(message = "idTypeRoom is not empty or null")
    private String idTypeRoom;
}
