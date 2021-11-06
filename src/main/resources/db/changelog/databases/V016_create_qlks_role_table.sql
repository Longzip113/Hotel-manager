CREATE TABLE qlks_role (
  id_role varchar(255) NOT NULL,
  code_role varchar(255) NOT NULL,
  name_role varchar(255) DEFAULT NULL,
  is_delete BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (id_role)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;