DROP TABLE IF EXISTS books;
CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255),
                       price DECIMAL(10, 2),
                       availability BOOLEAN,
                       rating INT
);
