CREATE TABLE lesson
(id BIGINT NOT NULL ,
 teacher_id BIGINT NOT NULL,
 discipline VARCHAR(255),
 homework_task VARCHAR(255),
 PRIMARY KEY (id),
 FOREIGN KEY (teacher_id) references teacher(id)

);

CREATE TABLE homework_completion_result
(
    id BIGINT NOT NULL ,
    teacher_id BIGINT NOT NULL ,
    student_id BIGINT NOT NULL ,
    lesson_id BIGINT NOT NULL ,
    task_completion_result VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (lesson_id) REFERENCES lesson(id)
);