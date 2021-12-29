package com.hotelManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotelManager.dtos.responses.QLKSArrangenmentCustomerResponse;
import com.hotelManager.dtos.responses.QLKSRegistrationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMapping(
        name = "QLKSRoomModelMapping",
        classes = @ConstructorResult(
                targetClass = QLKSRoomModel.class,
                columns = {
                        @ColumnResult(name = "id_room", type = String.class),
                        @ColumnResult(name = "name_room", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "name_type_room", type = String.class),
                        @ColumnResult(name = "name_employee", type = String.class),
                        @ColumnResult(name = "id_type_room", type = String.class),
                        @ColumnResult(name = "status", type = Integer.class)
                }))
public class QLKSRoomModel {

    @Id
    @JsonProperty("id")
    @Column(name = "id_room")
    private String id;

    @Column(name = "name_room")
    @JsonProperty("nameRoom")
    private String nameRoom;

    @Column(name = "description", nullable = false)
    @JsonProperty("description")
    private String description;

    @Column(name = "name_type_room", nullable = false)
    @JsonProperty("nameTypeRoom")
    private String nameTypeRoom;

    @Column(name = "name_employee", nullable = false)
    @JsonProperty("nameEmployee")
    private String nameEmployee;

    @Column(name = "id_type_room", nullable = false)
    @JsonProperty("idTypeRoom")
    private String idTypeRoom;

    @JsonProperty("status")
    @Column(name = "status", nullable = false)
    private long status;

    @Transient
    @JsonProperty("details")
    @ElementCollection(targetClass= QLKSDetailTypeRoomModel.class)
    List<QLKSDetailTypeRoomModel> details;

    @Transient
    @JsonProperty("infoRegistration")
    @ElementCollection(targetClass= QLKSRegistrationResponse.class)
    QLKSRegistrationResponse infoRegistration;

    @Transient
    @JsonProperty("infoCustomerBooking")
    @ElementCollection(targetClass= QLKSArrangenmentCustomerResponse.class)
    QLKSArrangenmentCustomerResponse infoCustomerBooking;

    public QLKSRoomModel(String id, String nameRoom, String description, String nameTypeRoom, String nameEmployee, String idTypeRoom, Integer status) {
        this.id = id;
        this.nameRoom = nameRoom;
        this.description = description;
        this.nameTypeRoom = nameTypeRoom;
        this.nameEmployee = nameEmployee;
        this.idTypeRoom = idTypeRoom;
        this.status = status;
    }
}
