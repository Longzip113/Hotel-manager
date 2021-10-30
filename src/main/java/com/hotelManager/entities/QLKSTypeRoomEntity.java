package com.hotelManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_type_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSTypeRoomEntity {

    @Id
    @Column(name = "ID_TYPE_ROOM")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "NAME_TYPE_ROOM", nullable = false)
    private String nameTypeRoom;

    @Column(name = "IS_DELETE", nullable = false)
    @JsonIgnore
    private Boolean isDelete;
}
