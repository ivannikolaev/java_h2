CREATE TABLE author
(
    name     VARCHAR(20) NOT NULL PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    email    VARCHAR(20) NOT NULL
);

CREATE TABLE note
(
    id       IDENTITY         NOT NULL PRIMARY KEY,
    author   VARCHAR(20)      NOT NULL,
    text     VARCHAR(100)     NOT NULL,
    mentions VARCHAR(20) ARRAY[10] NOT NULL DEFAULT '{}',
    FOREIGN KEY (author) REFERENCES author (name)
);