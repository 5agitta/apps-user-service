-- liquibase formatted sql
--- SELECT EXTRACT(EPOCH FROM NOW());
-- changeset Abbiirr:1699014942
DROP TABLE IF EXISTS "user";
CREATE TABLE tax.user (
    etin VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    phone VARCHAR(255),
    address VARCHAR(255),
    dob DATE,
    gender VARCHAR(255)
);
