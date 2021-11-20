package com.hotelManager.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@Data
public class DelegationRequest {

    @NotBlank(message = "nameDelegations is not empty or null")
    private String nameDelegations;

    @NotBlank(message = "nameCompany is not empty or null")
    private String nameCompany;

    @NotBlank(message = "idTeamManager is not empty or null")
    private String idTeamManager;

    private List<String> idCustomers;

}
