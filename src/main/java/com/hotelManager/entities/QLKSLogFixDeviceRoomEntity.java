package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_log_fix_device_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSLogFixDeviceRoomEntity {
    @Id
    @Column(name = "ID_LOG_FIX_DEVICE_ROOM")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_ROOM")
    private String idRoom;

    @Column(name = "ID_REGISTRATION_FORM", nullable = false)
    private String idRegistrationForm;

    @Column(name = "ID_EMPLOYEE", nullable = false)
    private String idEmployee;

    @Column(name = "ID_DEVICE", nullable = false)
    private String idDevice;

    @Column(name = "TYPE", nullable = false)
    private Integer type;

    @Column(name = "TIME_START", nullable = false)
    private Long timeStart;

    @Column(name = "TIME_END", nullable = false)
    private Long timeEnd;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

}
