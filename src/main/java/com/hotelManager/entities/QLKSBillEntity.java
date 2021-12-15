package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_bill")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSBillEntity {
    @Id
    @Column(name = "ID_BILL")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_REGISTRATION_FORM")
    private String idRegistrationForm;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private String idCustomer;

    @Column(name = "ID_ROOM", nullable = false)
    private String idRoom;

    @Column(name = "ROOM_RENT", nullable = false)
    private Long roomRent;

    @Column(name = "SERVICE_FEE", nullable = false)
    private Long serviceFee;

    @Column(name = "COSTS_INCURRED", nullable = false)
    private Long costsIncurred;

    @Column(name = "TOTAL_MONEY", nullable = false)
    private Long totalMoney;

    @Column(name = "DAY_OF_PAYMENT", nullable = false)
    private Long dayOfPayment;

    @Column(name = "ID_EMPLOYEE", nullable = false)
    private String idEmployee;

    @Column(name = "IS_DELETE", nullable = false)
    private Boolean isDelete;

}
