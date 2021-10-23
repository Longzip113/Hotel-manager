package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_delegation")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSDelegationEntity {
    @Id
    @Column(name = "ID_DELEGATIONS")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "NAME_DELEGATIONS")
    private String nameDelegations;

    @Column(name = "ID_TEAM_MANAGER", nullable = false)
    private String idTeamManager;

    @Column(name = "NAME_COMPANY", nullable = false)
    private String nameCompany;

    @Column(name = "NUMBER_OF_PEOPLE", nullable = false)
    private Integer numberOfPeople;

    @Column(name = "IS_DELETE", nullable = false)
    private Boolean isDelete;
}
