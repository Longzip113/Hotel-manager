CREATE TABLE qlks_service (
  id_service varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  name_service varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  price int NOT NULL DEFAULT 0,
  is_delete bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;