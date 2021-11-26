CREATE TABLE qlks_log_customer (
  id_log_customer varchar(255) NOT NULL,
  id_registration_form varchar(255) NOT NULL,
  id_customer varchar(255) NOT NULL,
  id_room varchar(255) NOT NULL, -- add new
  id_type varchar(255) NOT NULL,
  type int NOT NULL, -- add new lưu loại thiết bị và loại dịch vụ
  quantity int NOT NULL DEFAULT 0,
  log_time BIGINT  NOT NULL,
  description varchar(255) NOT NULL,
  total_price int NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;