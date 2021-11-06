CREATE TABLE qlks_detail_type_room (
   id_type_room varchar(255) NOT NULL,
   id_type varchar(255) DEFAULT NULL,
   id_detail varchar(255) DEFAULT NULL,
   type_detail int DEFAULT NULL,
   quantity int DEFAULT NULL,
   PRIMARY KEY (id_type_room)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;