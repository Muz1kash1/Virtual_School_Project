package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
  StudentEntity findBySnils(String Snils);

  void deleteBySnils(String s);

  StudentEntity findByBirthCertificate(String toString);
  @Query(value = "SELECT nextval('student_id_seq') from student_id_seq", nativeQuery = true)
  Long getNextId();
}
