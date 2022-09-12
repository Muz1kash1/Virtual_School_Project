CREATE TABLE student
(
    id                BIGSERIAL NOT NULL ,
    birth_certificate VARCHAR(32),
    birthday          DATE,
    name              VARCHAR(255),
    passport          VARCHAR(32),
    snils             VARCHAR(32),
    primary key (id)
);

CREATE TABLE teacher
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE school_class
(
    id BIGSERIAL NOT NULL,
    parallel SMALLINT,
    litera VARCHAR(1),
    PRIMARY KEY (id)
);

CREATE TABLE lesson
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE education_progress
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE homework_completion_result
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);