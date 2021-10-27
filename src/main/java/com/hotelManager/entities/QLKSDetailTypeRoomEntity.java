package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_detail_type_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSDetailTypeRoomEntity {
    @Id
    @Column(name = "ID_TYPE_ROOM")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_DETAIL")
    private String idDetail;

    @Column(name = "TYPE_DETAIL", nullable = false)
    private String typeDetail;

    @Column(name = "QUANTITY", nullable = false)
    private String quantity;

}