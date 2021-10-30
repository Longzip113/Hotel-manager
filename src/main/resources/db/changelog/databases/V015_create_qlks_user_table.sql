CREATE TABLE qlks_user (
  id_user varchar(255) NOT NULL,
  id_role varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  pass_word varchar(255) DEFAULT NULL,
  is_delete BIT(1) DEFAULT 0,
  PRIMARY KEY (id_user)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;