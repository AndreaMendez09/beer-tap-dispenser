CREATE TABLE dispenser (
    id SERIAL PRIMARY KEY,
    opened_at TIMESTAMP,
    closed_at TIMESTAMP,
    flow_volume DECIMAL(10, 3)
);

CREATE TABLE dispenser_log (
    id SERIAL PRIMARY KEY,
    entity_id INT,
    opened_at TIMESTAMP,
    closed_at TIMESTAMP,
    flow_volume DECIMAL(10, 3),
    FOREIGN KEY (entity_id) REFERENCES dispenser(id)
);
