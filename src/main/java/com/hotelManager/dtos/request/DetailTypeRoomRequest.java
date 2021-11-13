package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
public class DetailTypeRoomRequest {

    @NotBlank(message = "idTypeRoom is not empty or null")
    private String idTypeRoom;

    @NotBlank(message = "idDetailType is not empty or null")
    private String idDetailType;

    private Integer typeDetail;

    private Integer quantity;
}
