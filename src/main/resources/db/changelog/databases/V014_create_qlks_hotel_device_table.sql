CREATE TABLE qlks_hotel_device (
  id_hotel_device varchar(255) NOT NULL,
  name_hotel_device varchar(255) DEFAULT NULL,
  price int DEFAULT NULL,
  quantity int DEFAULT NULL,
  status BIT DEFAULT NULL,
  is_delete BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (id_hotel_device)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;