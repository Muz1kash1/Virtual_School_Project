CREATE TABLE lesson
(id BIGSERIAL  ,
 teacher_id BIGINT,
 discipline VARCHAR(255),
 homework_task VARCHAR(255),
 PRIMARY KEY (id),
 FOREIGN KEY (teacher_id) references teacher(id)

);

CREATE TABLE homework_completion_result
(
    id BIGSERIAL,
    teacher_id BIGINT,
    student_id BIGINT,
    lesson_id BIGINT,
    task_completion_result VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (lesson_id) REFERENCES lesson(id)
);