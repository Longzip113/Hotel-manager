CREATE TABLE qlks_log_fix_device_room (
  id_log_fix_device_room varchar(255) NOT NULL,
  id_room varchar(255) NOT NULL,
  id_registration_form varchar(255) NOT NULL,
  id_employee varchar(255) NOT NULL,
  id_thiet_bi varchar(255) NOT NULL,
  type int NOT NULL, -- khach san lam hu/ khach hang lam hu
  time_start BIGINT NOT NULL,
  time_end BIGINT NULL,
  price int NOT NULL, -- neu khach hang lam hu gia 0d
  PRIMARY KEY (id_log_fix_device_room)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;