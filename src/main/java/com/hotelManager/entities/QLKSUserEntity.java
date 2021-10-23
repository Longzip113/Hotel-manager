package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_user")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSUserEntity {
    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASS_WORD", nullable = false)
    private String passWord;

    @Column(name = "ID_ROLE", nullable = false)
    private String idRole;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted;
}
