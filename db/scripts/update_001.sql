CREATE TABLE if not exists users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email VARCHAR(255) UNIQUE,
    password TEXT
);

INSERT INTO users (name, email, password) VALUES ('molchanov', 'molchanov@mail.ru', 123);

CREATE TABLE if not exists papers (
    id SERIAL PRIMARY KEY,
    name TEXT,
    oldText TEXT,
    newText TEXT,
    created Timestamp,
    done boolean,
    user_id int not null references users(id),
    createUser VARCHAR(255),
    modifyUser VARCHAR(255)
);