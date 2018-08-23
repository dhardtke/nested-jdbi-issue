CREATE DATABASE issue;

USE issue;

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255),
  street VARCHAR(255),
  city VARCHAR(255)
);

INSERT INTO users (name, street, city) VALUES
  ('John Doe', 'Street 1', 'City 1'),
  ('Second User', 'Street 2', 'City 2');
