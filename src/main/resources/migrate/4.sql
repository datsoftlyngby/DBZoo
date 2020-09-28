CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL UNIQUE,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    salt BINARY(16) NOT NULL,
    secret BINARY(32) NOT NULL
);

UPDATE properties
SET value = '4'
WHERE name = "version";