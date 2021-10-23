CREATE TABLE v_node_ocr_detail (
  ocr_detail_id bigint NOT NULL AUTO_INCREMENT,
  ocr_id bigint NOT NULL,
  file_name VARCHAR(255) DEFAULT NULL,
  image TEXT DEFAULT NULL,
  list_line JSON DEFAULT NULL,
  other_images JSON DEFAULT NULL,
  page_number BIGINT DEFAULT NULL,
  text TEXT DEFAULT NULL,
  vimc_image TEXT DEFAULT NULL,
  PRIMARY KEY (ocr_detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;