package com.fabit.schoolapplication.infrastructure.persisnence.repository;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInClassRepository
    extends JpaRepository<StudentInClassEntity, Long> {

  StudentInClassEntity findByStudentId(Long id);

  void deleteAllByStudentId(Long id);

  void deleteAllBySchoolClassId(Long id);

}
