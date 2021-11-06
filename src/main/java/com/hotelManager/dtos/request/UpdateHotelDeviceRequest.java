package com.hotelManager.dtos.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class UpdateHotelDeviceRequest {

    private String nameHotelDevice;

    private Integer price;

    private Integer quantity;

}
