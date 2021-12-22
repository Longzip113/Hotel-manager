package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_log_clean_room")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSLogCleanRoomEntity {
    @Id
    @Column(name = "ID_LOG_CLEAN_ROOM")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "ID_ROOM")
    private String idRoom;

    @Column(name = "ID_EMPLOYEE", nullable = false)
    private String idEmployee;

    @Column(name = "TIME_START", nullable = false)
    private Long timeStart;

    @Column(name = "TIME_END", nullable = false)
    private Long timeEnd;

    @Column(name = "day_working", nullable = false)
    private String dayWorking;
}
