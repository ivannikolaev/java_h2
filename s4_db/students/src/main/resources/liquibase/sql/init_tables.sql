CREATE TABLE IF NOT EXISTS student
(
    id         bigserial       NOT NULL,
    name       varchar(255) NOT NULL,
    address    text         NOT NULL,
    department varchar(63)  NOT NULL,
    salary     bigint       NOT NULL default 100
);

INSERT INTO student (id, name, address, department)
VALUES (1, 'Ivan', 'Kolomenskaya, 1', 'Maths');
INSERT INTO student (id, name, address, department)
VALUES (1, 'Ivan', 'Kolomenskaya, 1', 'Foreign languages');
INSERT INTO student (id, name, address, department)
VALUES (2, 'Sergey', 'Tereshkova, 7', 'Foreign languages');
INSERT INTO student (id, name, address, department)
VALUES (3, 'Mikhail', 'Lenina, 3', 'Physics');