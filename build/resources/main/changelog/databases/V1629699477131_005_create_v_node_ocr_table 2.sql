CREATE TABLE v_node_ocr (
  ocr_id bigint NOT NULL AUTO_INCREMENT,
  approved_at bigint DEFAULT NULL,
  approved_by varchar(255) DEFAULT NULL,
  approved_status int DEFAULT NULL,
  content varchar(255) DEFAULT NULL,
  created_at bigint DEFAULT NULL,
  created_by varchar(255) DEFAULT NULL,
  locked_at bigint DEFAULT NULL,
  locked_by varchar(255) DEFAULT NULL,
  node_id varchar(255) DEFAULT NULL,
  status int NOT NULL DEFAULT '0',
  updated_at bigint DEFAULT NULL,
  updated_by varchar(255) DEFAULT NULL,
  PRIMARY KEY (ocr_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;