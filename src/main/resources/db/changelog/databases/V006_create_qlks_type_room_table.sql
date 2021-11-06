CREATE TABLE qlks_type_room (
  id_type_room varchar(50) NOT NULL,
  name_type_room varchar(50) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  price int DEFAULT NULL,
  is_delete BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id_type_room)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci