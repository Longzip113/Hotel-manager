package com.hotelManager.dtos.responses;

import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSRegistrationFormEntity;
import com.hotelManager.model.QLKSRoomModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Data
@Builder
public class QLKSLogCustomerResponse {

    private String idLogCustomer;

    private QLKSRegistrationFormEntity infoRegistration;

    private QLKSCustomerEntity infoCustomer;

    private String name;

    private QLKSRoomModel infoRoom;

    private Integer type;

    private Integer quantity;

    private Long time;

    private String idType;

    private String description;

    private Integer totalPrice;

}
