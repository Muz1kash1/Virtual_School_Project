ALTER TABLE school_class
    ADD CONSTRAINT uq_school_class UNIQUE(parallel, litera);

CREATE TABLE student_in_class (
    id BIGSERIAL PRIMARY KEY,
    school_class_id BIGINT REFERENCES school_class(id),
    student_id BIGINT REFERENCES student(id) UNIQUE
)