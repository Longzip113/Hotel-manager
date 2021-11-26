package com.hotelManager.dtos.responses;

import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSEmployeeEntity;
import com.hotelManager.model.QLKSDelegationModel;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
@Builder
public class QLKSRegistrationResponse {

    private String idRegistration;

    private Long bookingDate;

    private Long checkInDate;

    private Long checkOutDate;

    private String note;

    private List<QLKSRoomModel> rooms;

    private List<QLKSCustomerEntity> customers;

    private QLKSEmployeeModel employee;

    private QLKSDelegationModel delegation;

    private Integer numberOfChild;

    private Integer numberOfAdult;

    private Long intoMoney;

    private Integer type;

    private Integer status;

}
