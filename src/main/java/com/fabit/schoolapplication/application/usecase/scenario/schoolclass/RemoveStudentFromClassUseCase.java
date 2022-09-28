package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoveStudentFromClassUseCase {

  final SchoolClassService schoolClassService;

  /**
   * Отчислить студента из школьного класса.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор студента
   */
  public void execute(SchoolClassId schoolClassId, StudentId studentId) {
    schoolClassService.removeStudentFromClass(schoolClassId, studentId);
  }

}
