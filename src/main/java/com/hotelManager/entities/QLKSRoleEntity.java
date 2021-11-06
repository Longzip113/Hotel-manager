package com.hotelManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_role")
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

    @Column(name = "IS_DELETE", nullable = false)
    @JsonIgnore
    private boolean isDelete;
}
