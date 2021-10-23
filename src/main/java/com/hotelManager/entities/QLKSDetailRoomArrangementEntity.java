package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_detail_room_arrangement")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSDetailRoomArrangementEntity {
    @Id
    @Column(name = "id_detail_room_arrangement")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_ROOM")
    private String idRoom;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private String idCustomer;

    @Column(name = "ID_DETAIL", nullable = false)
    private String idDetail;

    @Column(name = "TYPE_DETAIL", nullable = false)
    private Integer typeDetail;

    @Column(name = "ID_REGISTRATION_FORM", nullable = false)
    private String idRegistrationForm;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

}
