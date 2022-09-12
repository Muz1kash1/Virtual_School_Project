ALTER TABLE education_progress ADD COLUMN teacherId BIGINT REFERENCES teacher(id);
ALTER TABLE education_progress ADD COLUMN lessonId BIGINT REFERENCES lesson(id);
ALTER TABLE education_progress ADD COLUMN studentId BIGINT REFERENCES student(id);
ALTER TABLE education_progress ADD COLUMN whenCreated TIMESTAMP;

CREATE TABLE Mark
(
    id BIGSERIAL,
    education_progress_id BIGINT REFERENCES education_progress(id),
    value INTEGER,
    PRIMARY KEY (id)
);