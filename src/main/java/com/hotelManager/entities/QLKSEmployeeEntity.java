package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_employee")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSEmployeeEntity {
    @Id
    @Column(name = "ID_EMPLOYEE")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name_employee")
    private String nameEmployee;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "identity_card", nullable = false)
    private String identityCard;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "verification")
    private String verification;

    @Column(name = "id_role")
    private String idRole;

    @Column(name = "is_delete", nullable = false)
    private boolean isDelete;
}
