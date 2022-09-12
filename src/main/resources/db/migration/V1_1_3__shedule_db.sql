CREATE TABLE lesson_for_schedule
(
    id                BIGSERIAL PRIMARY KEY,
    number_by_account INTEGER,
    discipline_name   VARCHAR(30),
    teacher_id        BIGSERIAL not null
);
CREATE TABLE school_day
(
    id                     BIGSERIAL PRIMARY KEY,
    day_of_week            VARCHAR(5),
    training_shift         VARCHAR(5),
    lesson_for_schedule_id BIGSERIAL NOT NULL REFERENCES lesson_for_schedule (id)
);
ALTER TABLE schedule
    ADD CONSTRAINT school_class_id
        FOREIGN KEY (id) REFERENCES school_class (id);

ALTER TABLE schedule
    ADD CONSTRAINT school_day_id
        FOREIGN KEY (id) REFERENCES school_day (id);