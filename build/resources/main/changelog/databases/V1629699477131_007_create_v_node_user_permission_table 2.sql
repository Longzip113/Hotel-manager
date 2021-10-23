CREATE TABLE v_node_user_permission (
  permission_id bigint NOT NULL AUTO_INCREMENT,
  node_id varchar(255) DEFAULT NULL,
  user_id varchar(255) DEFAULT NULL,
  is_read bit(1) NOT NULL DEFAULT 0,
  is_ocr bit(1) NOT NULL DEFAULT 0,
  is_approved bit(1) NOT NULL DEFAULT 0,
  is_view_properties bit(1) NOT NULL DEFAULT 0,
  is_edit_properties bit(1) NOT NULL DEFAULT 0,
  is_deleted bit(1) NOT NULL DEFAULT 0,
  is_view_permission bit(1) NOT NULL DEFAULT 0,
  is_edit_permission bit(1) NOT NULL DEFAULT 0,
  is_change_owner bit(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;