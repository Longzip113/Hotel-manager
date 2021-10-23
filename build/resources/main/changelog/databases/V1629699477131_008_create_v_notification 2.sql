CREATE TABLE v_notification (
  notification_id bigint NOT NULL AUTO_INCREMENT,
  created_at bigint DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  send_to varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  PRIMARY KEY (notification_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;