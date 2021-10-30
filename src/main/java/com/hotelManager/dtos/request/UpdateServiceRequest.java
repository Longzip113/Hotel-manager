package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UpdateServiceRequest {

    private String nameService;

    private Integer price;

}
