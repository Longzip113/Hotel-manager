package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_user", uniqueConstraints={
        @UniqueConstraint(columnNames = {"EMAIL", "ID_USER"})
})
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSRoleEntity {

    @Id
    @Column(name = "ID_ROLE")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "CODE_ROLE")
    private String codeRole;

    @Column(name = "NAME_ROLE", nullable = false)
    private String nameRole;
}
