CREATE TRIGGER `dispenser_update_log`
    AFTER UPDATE
    ON `dispenser`
    FOR EACH ROW
begin
    insert into dispenser_log (entity_id, opened_at, closed_at, flow_volume)
    values (OLD.id, OLD.opened_at, now(), OLD.flow_volume);
end