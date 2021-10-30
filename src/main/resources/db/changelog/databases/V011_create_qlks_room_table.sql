CREATE TABLE qlks_room (
  id_room varchar(255) NOT NULL,
  name_room varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  id_type_room varchar(255) DEFAULT NULL,
  status int DEFAULT NULL,
  housekeeping_order int DEFAULT NULL,
  id_housekeeping_staff varchar(255) DEFAULT NULL,
  id_registration_form varchar(255) DEFAULT NULL,
  is_delete BIT(1) DEFAULT 0,
  PRIMARY KEY (id_room)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;