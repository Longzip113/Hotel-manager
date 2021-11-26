CREATE TABLE qlks_room_arrangement (
  id_room_arrangement varchar(255) NOT NULL,
  id_room varchar(255) NOT NULL,
  id_customer varchar(255) DEFAULT NULL,
  id_registration_form varchar(255) DEFAULT NULL,
  is_delete BIT(1) DEFAULT 0,
  number_of_children int DEFAULT NULL,
  PRIMARY KEY (id_room_arrangement)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;