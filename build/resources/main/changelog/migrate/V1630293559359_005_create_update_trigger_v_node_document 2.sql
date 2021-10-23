CREATE TRIGGER Before_Update_v_node_document BEFORE UPDATE ON v_node_document FOR EACH ROW BEGIN
  IF (EXISTS(
            SELECT 1 FROM v_node_document d
            JOIN v_node_base b
            ON b.node_id = d.node_id
                AND b.is_deleted = false
                AND b.type = 1
                AND d.extension = NEW.extension
                AND d.node_id = NEW.node_id
            )
        ) THEN
    SIGNAL SQLSTATE VALUE '45002' SET MESSAGE_TEXT = 'Update failed due to duplicate v_node_document';
  END IF;
END;