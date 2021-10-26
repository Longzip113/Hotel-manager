CREATE TABLE qlks_role (
  id_role varchar(255) NOT NULL,
  code_role varchar(255) NOT NULL,
  name_role varchar(255) DEFAULT NULL,
  PRIMARY KEY (id_role)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;