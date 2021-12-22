CREATE TABLE qlks_log_clean_room (
  id_log_clean_room varchar(255) NOT NULL,
  id_room varchar(255) NOT NULL,
  id_employee varchar(255) NOT NULL,
  time_start BIGINT NOT NULL,
  time_end BIGINT NULL,
  day_working varchar(55) NULL,
  PRIMARY KEY (id_log_clean_room)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;