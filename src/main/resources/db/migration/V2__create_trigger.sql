CREATE OR REPLACE FUNCTION trigger_dispenser_update_log()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO dispenser_log (entity_id, opened_at, closed_at, flow_volume)
    VALUES (OLD.id, OLD.opened_at, OLD.closed_at, OLD.flow_volume);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER dispenser_update_log
AFTER UPDATE ON dispenser
FOR EACH ROW
EXECUTE FUNCTION trigger_dispenser_update_log();