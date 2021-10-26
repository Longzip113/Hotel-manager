  CREATE TABLE qlks_detail_room_arrangement (
  id_detail_room_arrangement varchar(255) NOT NULL,
  id_room varchar(255) NOT NULL,
  id_customer varchar(255) DEFAULT NULL,
  id_registration_form varchar(255) DEFAULT NULL,
  id_detail varchar(255) DEFAULT NULL,
  type_detail int DEFAULT NULL,
  quantity int DEFAULT NULL,
  PRIMARY KEY (id_detail_room_arrangement)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;