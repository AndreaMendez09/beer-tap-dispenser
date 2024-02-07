CREATE TABLE dispenser (
    id INT PRIMARY KEY,
    opened_at TIMESTAMP NOT NULL,
    closed_at TIMESTAMP,
    flow_volume DECIMAL(10, 3) NOT NULL
);

CREATE TABLE dispenser_log (
    id SERIAL PRIMARY KEY,
    entity_id REFERENCES dispenser(id),
    opened_at TIMESTAMP,
    closed_at TIMESTAMP,
    flow_volume DECIMAL(10, 3) NOT NULL
);
