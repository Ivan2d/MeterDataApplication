CREATE TABLE house
(
    id           SERIAL PRIMARY KEY,
    house_number INT,
    address      VARCHAR(255)
);

CREATE TABLE apartment
(
    id               SERIAL PRIMARY KEY,
    apartment_number VARCHAR(10),
    area             DECIMAL(10, 2),
    house_id         INT,
    FOREIGN KEY (house_id) REFERENCES house (id)
);

CREATE TABLE meter_type
(
    id   SERIAL PRIMARY KEY,
    type VARCHAR(50)
);

CREATE TABLE meter
(
    id                SERIAL PRIMARY KEY,
    installation_date DATE,
    end_date          DATE,
    apartment_id      INT,
    type_id           INT,
    FOREIGN KEY (type_id) REFERENCES meter_type (id),
    FOREIGN KEY (apartment_id) REFERENCES apartment (id)
);

CREATE TABLE meter_reading
(
    id        SERIAL PRIMARY KEY,
    reading   DECIMAL(10, 2),
    date_time TIMESTAMP,
    meter_id  INT,
    FOREIGN KEY (meter_id) REFERENCES meter (id)
);

CREATE TABLE account
(
    id             SERIAL PRIMARY KEY,
    account_number INT,
    apartment_id   INT,
    FOREIGN KEY (apartment_id) REFERENCES apartment (id)
);

CREATE TABLE owner
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    phone      VARCHAR(20)
);

CREATE TABLE apartment_owner
(
    id           SERIAL PRIMARY KEY,
    apartment_id INT,
    owner_id     INT,
    FOREIGN KEY (apartment_id) REFERENCES apartment (id),
    FOREIGN KEY (owner_id) REFERENCES owner (id),
    UNIQUE (apartment_id, owner_id)
);

CREATE TABLE payment_history
(
    id         SERIAL PRIMARY KEY,
    amount     DECIMAL(10, 2),
    date       TIMESTAMP,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES account (id)
);