CREATE TABLE apartment (
                           id SERIAL PRIMARY KEY,
                           apartment_number INT,
                           area DECIMAL(10, 2),
                           address VARCHAR(255)
);

CREATE TABLE house (
                       id SERIAL PRIMARY KEY,
                       house_number INT,
                       address VARCHAR(255)
);

CREATE TABLE meter (
                       id SERIAL PRIMARY KEY,
                       type VARCHAR(50),
                       reading DECIMAL(10, 2),
                       installation_date DATE,
                       apartment_id INT,
                       FOREIGN KEY (apartment_id) REFERENCES apartment(id)
);

CREATE TABLE account (
                         id SERIAL PRIMARY KEY,
                         account_number INT,
                         balance DECIMAL(10, 2),
                         apartment_id INT,
                         FOREIGN KEY (apartment_id) REFERENCES apartment(id)
);

CREATE TABLE owner (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       phone VARCHAR(20),
                       apartment_id INT,
                       FOREIGN KEY (apartment_id) REFERENCES apartment(id)
);
