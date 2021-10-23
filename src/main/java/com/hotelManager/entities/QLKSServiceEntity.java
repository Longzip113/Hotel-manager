package com.hotelManager.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "qlks_service")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QLKSServiceEntity {
    @Id
    @Column(name = "ID_SERVICE")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "NAME_SERVICE")
    private String nameService;

    @Column(name = "PRICE", nullable = false)
    private String price;

    @Column(name = "IS_DELETE", nullable = false)
    private Long isDelete;
}
