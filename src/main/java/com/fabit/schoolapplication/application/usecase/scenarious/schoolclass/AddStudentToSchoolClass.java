package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddStudentToSchoolClass {

  private final StudentInClassRepository studentInClassRepository;

  /**
   * Юзкейс добавления ученика в класс.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика, которого следует добавить в школьный класс
   * @return StudentInClassEntity
   */
  public StudentInClassEntity execute(SchoolClassId schoolClassId, StudentId studentId) {
    return studentInClassRepository.save(
        StudentInClassEntity.of(schoolClassId.getValue(), studentId.getValue()));
  }

}
