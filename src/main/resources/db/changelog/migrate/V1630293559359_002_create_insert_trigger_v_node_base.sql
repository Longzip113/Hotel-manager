CREATE TRIGGER Before_Insert_qlks_role BEFORE INSERT ON qlks_role FOR EACH ROW BEGIN
  IF (EXISTS(
        SELECT 1
        FROM qlks_role
        WHERE 1 = 1)
        ) THEN
    SIGNAL SQLSTATE VALUE '45000' SET MESSAGE_TEXT = 'Insert failed due to duplicate v_node_base';
  END IF;
END;