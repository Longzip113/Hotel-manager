package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UpdateTypeRoomRequest {

    private String nameTypeRoom;

    private String description;

    private Integer price;

}
