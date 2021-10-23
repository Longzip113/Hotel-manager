package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_clean_schedule")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSCleanScheduleEntity {
    @Id
    @Column(name = "ID_CLEAN_SCHEDULE")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "id_employee")
    private String idEmployee;

    @Column(name = "id_room", nullable = false)
    private String idRoom;

    @Column(name = "day_working", nullable = false)
    private Long dayWorking;
}
