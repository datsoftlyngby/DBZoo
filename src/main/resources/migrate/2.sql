ALTER TABLE animals ADD COLUMN last_fed TIMESTAMP NOT NULL default(NOW());

UPDATE properties
SET value = '2'
WHERE name = "version";
