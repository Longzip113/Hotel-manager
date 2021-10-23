package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSRoomEntity {
    @Id
    @Column(name = "ID_ROOM")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "NAME_ROOM")
    private String nameRoom;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "ID_TYPE_ROOM", nullable = false)
    private String idTypeRoom;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "HOUSEKEEPING_ORDER", nullable = false)
    private String housekeepingOrder;

    @Column(name = "ID_HOUSEKEEPING_STAFF", nullable = false)
    private Integer idHousekeepingStaff;

    @Column(name = "ID_REGISTRATION_FORM", nullable = false)
    private String idRegistrationForm;

    @Column(name = "IS_DELETE", nullable = false)
    private Boolean isDelete;

}
