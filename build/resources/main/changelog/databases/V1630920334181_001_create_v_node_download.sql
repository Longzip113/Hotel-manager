CREATE TABLE v_node_download (
  node_key varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  node_id varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  expired_at bigint NOT NULL,
  PRIMARY KEY (node_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;