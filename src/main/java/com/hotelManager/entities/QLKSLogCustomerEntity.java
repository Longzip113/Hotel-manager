package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_log_customer")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSLogCustomerEntity {
    @Id
    @Column(name = "ID_LOG_CUSTOMER")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_REGISTRATION_FORM")
    private String nameEmployee;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private String gender;

    @Column(name = "ID_ROOM", nullable = false)
    private String idRoom;

    @Column(name = "TYPE_ACTION", nullable = false)
    private Integer typeAction;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "LOG_TIME", nullable = false)
    private Long logTime;

    @Column(name = "ACTION", nullable = false)
    private Integer action;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;

}
