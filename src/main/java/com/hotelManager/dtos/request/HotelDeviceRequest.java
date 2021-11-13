package com.hotelManager.dtos.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Data
@Builder
public class HotelDeviceRequest {

    @NotBlank(message = "nameHotelDevice is not empty or null")
    private String nameHotelDevice;

    private Integer price;

    private Integer quantity;
}
