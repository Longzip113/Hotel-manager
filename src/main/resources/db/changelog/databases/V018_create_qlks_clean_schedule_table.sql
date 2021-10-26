CREATE TABLE qlks_clean_schedule (
  id_clean_schedule varchar(255) NOT NULL,
  id_employee varchar(255) NOT NULL,
  id_room varchar(255) DEFAULT NULL,
  day_working bigint DEFAULT NULL,
  PRIMARY KEY (id_clean_schedule)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;