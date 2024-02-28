DROP TABLE student;

CREATE TABLE IF NOT EXISTS student
(
    id         bigserial       NOT NULL PRIMARY KEY,
    name       varchar(255) NOT NULL,
    address    text         NOT NULL,
    salary     bigint       NOT NULL default 100
);

drop table student;

CREATE TABLE IF NOT EXISTS department
(
    id   bigint       NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS assignment
(
    st_id         bigint NOT NULL REFERENCES student(id),
    dept_id       bigint NOT NULL REFERENCES department(id)
);