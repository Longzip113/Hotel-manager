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
        name = "QLKSDetailTypeRoomModelMapping",
        classes = @ConstructorResult(
                targetClass = QLKSDetailTypeRoomModel.class,
                columns = {
                        @ColumnResult(name = "id_type_room", type = String.class),
                        @ColumnResult(name = "type_detail", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "id_detail", type = String.class),
                        @ColumnResult(name = "quantity", type = Integer.class)
                }))
public class QLKSDetailTypeRoomModel {

    @Id
    @Column(name = "id_type_room")
    private String id;

    @Column(name = "type_detail")
    private Integer typeDetail;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "id_detail", nullable = false)
    private String idType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
