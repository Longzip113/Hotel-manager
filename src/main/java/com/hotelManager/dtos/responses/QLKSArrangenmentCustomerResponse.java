package com.hotelManager.dtos.responses;

import com.hotelManager.entities.QLKSCustomerEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QLKSArrangenmentCustomerResponse {

    private Integer numberOfPeople;

    private Integer numberOfChild;

    private List<QLKSCustomerEntity> customers;
}
