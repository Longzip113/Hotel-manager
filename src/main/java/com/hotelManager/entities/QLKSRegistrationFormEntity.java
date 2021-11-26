package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_registration_form")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSRegistrationFormEntity {
    @Id
    @Column(name = "id_registration_form")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "booking_date")
    private Long bookingDate;

    @Column(name = "CHECK_IN_DATE", nullable = false)
    private Long checkInDate;

    @Column(name = "CHECK_OUT_DATE", nullable = false)
    private Long checkOutDate;

    @Column(name = "NOTE", nullable = false)
    private String note;

    @Column(name = "ID_ROOM", nullable = false)
    private String idRoom;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private String idCustomer;

    @Column(name = "ID_EMPLOYEE", nullable = false)
    private String idEmployee;

    @Column(name = "NUMBER_OF_ADULT", nullable = false)
    private Integer numberOfAdult;

    @Column(name = "NUMBER_OF_CHILD", nullable = false)
    private Integer numberOfChild;

    @Column(name = "TYPE", nullable = false)
    private Integer type;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @Column(name = "ID_DELEGATION", nullable = false)
    private String idDelegation;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    @Column(name = "INTO_MONEY", nullable = false)
    private Long intoMoney;

}
