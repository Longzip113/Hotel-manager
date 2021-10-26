CREATE TABLE qlks_customer (
  id_customer varchar(255) NOT NULL,
  name varchar(255) DEFAULT NULL,
  card varchar(255) DEFAULT NULL,
  nationality varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  is_delete BIT(1) DEFAULT 0,
  PRIMARY KEY (id_customer)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;