CREATE TRIGGER Before_Update_v_node_base BEFORE UPDATE ON v_node_base FOR EACH ROW BEGIN
  IF (EXISTS(
        SELECT 1
        FROM v_node_base
        WHERE path = NEW.path
            AND display_name = NEW.display_name
            AND is_deleted = NEW.is_deleted
            AND NEW.is_deleted = false
            AND node_id <> NEW.node_id
            AND type = NEW.type
            AND NEW.type = 0)
        ) THEN
    SIGNAL SQLSTATE VALUE '45001' SET MESSAGE_TEXT = 'Update failed due to duplicate v_node_base';
  END IF;
END;