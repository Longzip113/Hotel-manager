package com.hotelManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        name = "QLKSEmployeeModelMapping",
        classes = @ConstructorResult(
                targetClass = QLKSEmployeeModel.class,
                columns = {
                        @ColumnResult(name = "id_employee", type = String.class),
                        @ColumnResult(name = "name_employee", type = String.class),
                        @ColumnResult(name = "gender"),
                        @ColumnResult(name = "identity_card"),
                        @ColumnResult(name = "address"),
                        @ColumnResult(name = "phone_number"),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "code_role", type = String.class),
                        @ColumnResult(name = "name_role", type = String.class),
                        @ColumnResult(name = "id_role", type = String.class)
                }))
public class QLKSEmployeeModel {

    @Id
    @JsonProperty("id")
    @Column(name = "id_employee")
    private String id;

    @Column(name = "name_employee")
    @JsonProperty("nameEmployee")
    private String nameEmployee;

    @Column(name = "gender", nullable = false)
    @JsonProperty("gender")
    private String gender;

    @Column(name = "identity_card", nullable = false)
    @JsonProperty("identityCard")
    private String identityCard;

    @Column(name = "address", nullable = false)
    @JsonProperty("address")
    private String address;

    @Column(name = "phone_number", nullable = false)
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "code_role")
    @JsonProperty("codeRole")
    private String codeRole;

    @Column(name = "name_role", nullable = false)
    @JsonProperty("nameRole")
    private String nameRole;

    @Column(name = "id_role", nullable = false)
    @JsonProperty("idRole")
    private String idRole;
}
