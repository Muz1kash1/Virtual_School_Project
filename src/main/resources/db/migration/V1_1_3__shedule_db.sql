CREATE TABLE lesson_for_schedule
(
    id                bigserial PRIMARY KEY,
    number_by_account integer     not null,
    discipline_name   varchar(30) not null,
    teacher_id        bigserial   not null
);
CREATE TABLE school_day
(
    id bigserial PRIMARY KEY,
    day_of_week    varchar(5) not null,
    training_shift varchar(5) not null,
    lesson_for_schedule_id bigserial NOT NULL REFERENCES lesson_for_schedule(id)
);
ALTER TABLE schedule
    add constraint school_class_id
        foreign key (id) references school_class (id);

ALTER TABLE schedule
    add constraint school_day_id
        foreign key (id) references school_day (id);