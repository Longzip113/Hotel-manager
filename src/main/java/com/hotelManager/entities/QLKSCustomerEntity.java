package com.hotelManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_customer")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSCustomerEntity {
    @Id
    @Column(name = "ID_CUSTOMER")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CARD", nullable = false)
    private String card;

    @Column(name = "NATIONALITY", nullable = false)
    private String nationality;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "IS_DELETE", nullable = false)
    @JsonIgnore
    private boolean isDelete;
}
