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
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE lesson
(
    id BIGSERIAL NOT NULL ,
    teacher_id BIGSERIAL NOT NULL ,
    student_id BIGSERIAL NOT NULL ,
    lesson_id BIGSERIAL NOT NULL ,
    task_completion_result VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (lesson_id) REFERENCES lesson(id)
);

CREATE TABLE education_progress
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE homework_completion_result
(
    id BIGSERIAL NOT NULL ,
    teacher_id BIGSERIAL NOT NULL,
    discipline VARCHAR(255),
    homework_task VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) references teacher(id)
);

CREATE TABLE schedule
(
    id BIGSERIAL NOT NULL ,
    PRIMARY KEY (id)
);