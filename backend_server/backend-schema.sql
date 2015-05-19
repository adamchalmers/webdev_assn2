BEGIN;
DROP TABLE IF EXISTS order_state;
CREATE TABLE order_state (
    order_state_id INTEGER PRIMARY KEY,
    order_state VARCHAR
);

INSERT INTO order_state VALUES
(1, "processing"),
(2, "shipped"),
(3, "discarded");


DROP TABLE IF EXISTS delivery_price;
CREATE TABLE delivery_price (
    delivery_charge_id INTEGER PRIMARY KEY,
    place_name VARCHAR(128),
    fixed_cost FLOAT,
    variable_cost FLOAT
);

INSERT INTO delivery_price VALUES
(1, "sydney",       12.00, 2.00),
(2, "melbourne",    20.00, 2.50),
(3, "adelaide",     25.00, 3.00),
(4, "perth",        30.00, 2.50),
(5, "darwin",       35.00, 3.50),
(6, "brisbane",     15.00, 2.00),
(7, "hobart",       30.00, 3.50),
(8, "canberra",     12.00, 2.00);


DROP TABLE IF EXISTS photo_price;
CREATE TABLE photo_price (
    photo_price_id INTEGER PRIMARY KEY,
    fixed_cost FLOAT,
    variable_cost FLOAT
);

INSERT INTO photo_price VALUES
(1, 5.00, 0.50);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    order_id INTEGER PRIMARY KEY,
    user_id INTEGER,
    address TEXT,
    order_state_id INTEGER,
    order_price FLOAT,
    shipping_price FLOAT
);

INSERT INTO orders VALUES
(1, 1, "hello world", 1, 0, 0),
(2, 1, "world hello", 1, 0, 0),
(3, 1, "elloh world", 2, 0, 0);

DROP TABLE IF EXISTS order_item;
CREATE TABLE order_item (
    order_item_id INTEGER PRIMARY KEY,
    order_id INTEGER,
    item_id INTEGER,
    quantity INTEGER
);

INSERT INTO order_item VALUES
(1, 1, 58, 2),
(2, 1, 59, 1),
(3, 1, 60, 3),
(4, 2, 61, 4),
(5, 2, 62, 1),
(6, 3, 63, 1);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_id INTEGER PRIMARY KEY,
    user_name VARCHAR(64),
    admin BOOLEAN DEFAULT FALSE
);

INSERT INTO users VALUES 
(1, "user1",    0),
(2, "user2",    0),
(3, "admin1",   1);

COMMIT;
