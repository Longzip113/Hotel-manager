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
    private String bookingDate;

    @Column(name = "CHECK_IN_DATE", nullable = false)
    private String checkInDate;

    @Column(name = "CHECK_OUT_DATE", nullable = false)
    private String checkOutDate;

    @Column(name = "NOTE", nullable = false)
    private String note;

    @Column(name = "ID_ROOM", nullable = false)
    private String idRoom;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private boolean idCustomer;

    @Column(name = "ID_EMPLOYEE", nullable = false)
    private String idEmployee;

    @Column(name = "NUMBER_OF_ADULT", nullable = false)
    private String numberOfAdult;

    @Column(name = "NUMBER_OF_CHILD", nullable = false)
    private String numberOfChild;

    @Column(name = "INTO_MONEY", nullable = false)
    private String intoMoney;

}
