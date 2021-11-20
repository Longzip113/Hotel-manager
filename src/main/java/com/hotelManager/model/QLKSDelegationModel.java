package com.hotelManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "QLKSDelegationModelMapping",
        classes = @ConstructorResult(
                targetClass = QLKSDelegationModel.class,
                columns = {
                        @ColumnResult(name = "ID_DELEGATIONS", type = String.class),
                        @ColumnResult(name = "NAME_DELEGATIONS", type = String.class),
                        @ColumnResult(name = "nameManager", type = String.class),
                        @ColumnResult(name = "idManager", type = String.class),
                        @ColumnResult(name = "ID_CUSTOMER", type = String.class),
                        @ColumnResult(name = "NAME_COMPANY", type = String.class),
                        @ColumnResult(name = "NUMBER_OF_PEOPLE", type = Integer.class)
                }))
public class QLKSDelegationModel {

    @Id
    @Column(name = "ID_DELEGATIONS")
    private String id;

    @Column(name = "NAME_DELEGATIONS")
    private String nameDelegations;

    @Column(name = "nameManager", nullable = false)
    private String nameManager;

    @Column(name = "idManager", nullable = false)
    private String idManager;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private String idCustomer;

    @Column(name = "NAME_COMPANY", nullable = false)
    private String nameCompany;

    @Column(name = "NUMBER_OF_PEOPLE", nullable = false)
    private Integer numberOfPeople;
}
