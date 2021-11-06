CREATE TABLE qlks_employee (
  id_employee varchar(255) NOT NULL,
  name_employee varchar(255) DEFAULT NULL,
  gender varchar(255) DEFAULT NULL,
  identity_card varchar(255) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  id_role varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  pass_word varchar(255) DEFAULT NULL,
  verification varchar(255) DEFAULT NULL,
  is_delete BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id_employee)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;