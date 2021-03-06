package com.hotelManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer status;

    @Column(name = "STATUS_CLEAR", nullable = false)
    private Integer statusClear;

    @Column(name = "id_employee_clear", nullable = false)
    private String idEmployeeClear;

    @Column(name = "ID_HOUSEKEEPING_STAFF", nullable = false)
    private String idHousekeepingStaff;

    @Column(name = "ID_REGISTRATION_FORM", nullable = false)
    private String idRegistrationForm;

    @Column(name = "IS_DELETE", nullable = false)
    @JsonIgnore
    private Boolean isDelete;

}
