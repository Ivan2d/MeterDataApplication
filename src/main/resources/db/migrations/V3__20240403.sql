CREATE TABLE IF NOT EXISTS notification
(
    id          SERIAL PRIMARY KEY      NOT NULL,
    name        VARCHAR(128)            NOT NULL,
    description TEXT                    NOT NULL,
    created_at  TIMESTAMP DEFAULT NOW() NOT NULL,
    email       VARCHAR(256)            NOT NULL,
    type        VARCHAR(32)             NOT NULL
);