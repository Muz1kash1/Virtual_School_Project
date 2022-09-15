package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
  StudentEntity findBySnils(String Snils);

  void deleteBySnils(String s);

  StudentEntity findByBirthCertificate(String toString);
}
