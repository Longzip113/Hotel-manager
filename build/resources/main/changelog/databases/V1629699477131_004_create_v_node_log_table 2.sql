CREATE TABLE v_node_log (
  log_id bigint NOT NULL AUTO_INCREMENT,
  action int NOT NULL,
  created_at bigint NOT NULL,
  created_by varchar(255) NOT NULL,
  node_id varchar(255) DEFAULT NULL,
  note varchar(255) NOT NULL,
  PRIMARY KEY (log_id)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;