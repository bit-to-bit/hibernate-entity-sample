CREATE SCHEMA IF NOT EXISTS SpaceTravel;

SET SCHEMA SpaceTravel;

DROP TABLE Client IF EXISTS;
DROP TABLE Planet IF EXISTS;
DROP TABLE Ticket IF EXISTS;

CREATE TABLE IF NOT EXISTS Client(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(200) CHECK (CHAR_LENGTH(name) >= 3)
);

COMMENT ON TABLE Client IS 'Клієнти';
COMMENT ON COLUMN Client.id IS 'ID клієнта';
COMMENT ON COLUMN Client.name IS 'Імя клієнта';

CREATE TABLE IF NOT EXISTS Planet(
    id VARCHAR(50) NOT NULL CHECK (CHAR_LENGTH(REGEXP_REPLACE(id, '[A-Z]', '', 'c')) = 0 AND CHAR_LENGTH(id) >=1) PRIMARY KEY,
    name VARCHAR(500) CHECK (CHAR_LENGTH(name) >= 1)
);

COMMENT ON TABLE Planet IS 'Планети';
COMMENT ON COLUMN Planet.id IS 'ID планети';
COMMENT ON COLUMN Planet.name IS 'Назва планети';

CREATE TABLE IF NOT EXISTS Ticket(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    client_id BIGINT NOT NULL,
    from_planet_id VARCHAR(50) NOT NULL,
    to_planet_id VARCHAR(50) NOT NULL,
    CONSTRAINT fk_ticket_client FOREIGN KEY (client_id) REFERENCES client(ID) ON DELETE NO ACTION,
    CONSTRAINT fk_ticket_planet_from FOREIGN KEY (from_planet_id) REFERENCES planet(id) ON DELETE NO ACTION,
    CONSTRAINT fk_ticket_planet_to FOREIGN KEY (to_planet_id) REFERENCES planet(id) ON DELETE NO ACTION
);

COMMENT ON TABLE Ticket IS 'Квитки';
COMMENT ON COLUMN Ticket.id IS 'ID квитка';
COMMENT ON COLUMN Ticket.created_at IS 'Дата в UTC, коли був створений цей квиток';
COMMENT ON COLUMN Ticket.client_id IS 'ID клієнта, якому належить цей квиток';
COMMENT ON COLUMN Ticket.from_planet_id IS 'ID планети, звідки відправляється пасажир';
COMMENT ON COLUMN Ticket.to_planet_id IS 'ID планети, куди летить пасажир';