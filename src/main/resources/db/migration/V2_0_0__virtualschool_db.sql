CREATE TABLE student
(
    id                BIGSERIAL NOT NULL,
    birth_certificate VARCHAR(32),
    birthday          VARCHAR(32),
    name              VARCHAR(255),
    passport          VARCHAR(32),
    snils             VARCHAR(32),
    PRIMARY KEY (id)
);

CREATE TABLE teacher
(
    id        BIGSERIAL NOT NULL,
    full_name VARCHAR(255),
    passport  VARCHAR(255) UNIQUE,
    snils     VARCHAR(255) UNIQUE,
    is_active BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE school_class
(
    id       BIGSERIAL NOT NULL,
    parallel SMALLINT,
    litera   VARCHAR(1),
    PRIMARY KEY (id),
    CONSTRAINT uq_school_class UNIQUE (parallel, litera)
);

CREATE TABLE student_in_class
(
    id              BIGSERIAL PRIMARY KEY,
    school_class_id BIGINT REFERENCES school_class (id),
    student_id      BIGINT REFERENCES student (id) UNIQUE
);

CREATE TABLE lesson
(
    id            BIGSERIAL,
    teacher_id    BIGINT,
    discipline    VARCHAR(255),
    homework_task VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) references teacher (id)
);

CREATE TABLE homework_completion_result
(
    id                     BIGSERIAL,
    teacher_id             BIGINT,
    student_id             BIGINT,
    lesson_id              BIGINT,
    task_completion_result VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE homework_for_class
(
    id                     BIGSERIAL,
    discipline             VARCHAR(255),
    homework_task          VARCHAR(255),
    school_class_entity_id BIGINT,
    date                   DATE,
    PRIMARY KEY (id)
);

CREATE TABLE loaded_homework
(
    id                     BIGSERIAL,
    student_id             BIGINT,
    homework_for_class_id  BIGINT,
    task_completion_result VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE Mark
(
    id                    BIGSERIAL,
    value                 INTEGER,
    PRIMARY KEY (id)
);