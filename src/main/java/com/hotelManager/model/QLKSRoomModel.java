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
        name = "QLKSRoomModelMapping",
        classes = @ConstructorResult(
                targetClass = QLKSRoomModel.class,
                columns = {
                        @ColumnResult(name = "id_room", type = String.class),
                        @ColumnResult(name = "name_room", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "name_type_room", type = String.class),
                        @ColumnResult(name = "name_employee", type = String.class),
                        @ColumnResult(name = "housekeeping_order", type = String.class)
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

    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    private Integer status;

    @Column(name = "name_type_room", nullable = false)
    @JsonProperty("nameTypeRoom")
    private String nameTypeRoom;

    @Column(name = "name_employee", nullable = false)
    @JsonProperty("nameEmployee")
    private String nameEmployee;

    @Column(name = "housekeeping_order", nullable = false)
    @JsonProperty("housekeepingOrder")
    private String housekeepingOrder;
}