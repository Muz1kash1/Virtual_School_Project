package com.fabit.schoolapplication.application.usecase.virtualschool.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveStudentFromSchoolClass {

  private final StudentInClassRepository studentInClassRepository;

  /**
   * Юзкейс отчисления ученика из школьного класса.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId - идентификатор ученика
   */
  @Transactional
  public void execute(SchoolClassId schoolClassId, StudentId studentId) {
    studentInClassRepository.delete(
        StudentInClassEntity.of(schoolClassId.getValue(), studentId.getValue()));
    studentInClassRepository.deleteAllByStudentId(studentId.getValue());
  }

}
