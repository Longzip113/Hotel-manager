package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_room_arrangement")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSRoomArrangementEntity {
    @Id
    @Column(name = "ID_ROOM_ARRANGEMENT")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_ROOM")
    private String idRoom;

    @Column(name = "ID_CUSTOMER", nullable = false)
    private String idCustomer;

    @Column(name = "NUMBER_OF_CHILDREN", nullable = false)
    private String numberOfChildren;

    @Column(name = "PRICE_ARRANGEMENT", nullable = false)
    private Integer priceArrangement;

    @Column(name = "ID_REGISTRATION_FORM", nullable = false)
    private String idRegistrationForm;

    @Column(name = "IS_DELETE", nullable = false)
    private Boolean isDelete;

}
