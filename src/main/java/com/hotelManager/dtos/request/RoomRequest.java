package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class RoomRequest {

    @NotBlank(message = "nameRoom is not empty or null")
    private String nameRoom;

    @NotBlank(message = "description is not empty or null")
    private String description;

    @NotBlank(message = "idTypeRoom is not empty or null")
    private String idTypeRoom;
}
