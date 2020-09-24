
DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25),
    birthday DATE,
    type int
);

UPDATE properties 
SET value = '1' 
WHERE name = "version";