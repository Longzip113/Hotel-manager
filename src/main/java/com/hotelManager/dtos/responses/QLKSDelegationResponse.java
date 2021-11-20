package com.hotelManager.dtos.responses;

import com.hotelManager.entities.QLKSCustomerEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@Builder
public class QLKSDelegationResponse {

    private String id;

    private String nameDelegations;

    private String nameManager;

    private String nameCompany;

    private Integer numberOfPeople;

    private String idManager;

    List<QLKSCustomerEntity> customers;

}
