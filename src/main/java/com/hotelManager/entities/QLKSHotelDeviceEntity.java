package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_hotel_device")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSHotelDeviceEntity {
    @Id
    @Column(name = "ID_HOTEL_DEVICE")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "NAME_HOTEL_DEVICE")
    private String nameHotelDevice;

    @Column(name = "PRICE", nullable = false)
    private String price;

    @Column(name = "QUANTITY", nullable = false)
    private String quantity;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "IS_DELETE", nullable = false)
    private boolean isDeleted;
}
