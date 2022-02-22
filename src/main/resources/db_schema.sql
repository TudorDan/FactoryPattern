CREATE EXTENSION IF NOT EXISTS "pgcrypto";
DROP TABLE IF EXISTS items;

CREATE TABLE items (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price NUMERIC(9, 4)
);