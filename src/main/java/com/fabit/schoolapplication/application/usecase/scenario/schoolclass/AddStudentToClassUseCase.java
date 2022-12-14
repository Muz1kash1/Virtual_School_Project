package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddStudentToClassUseCase {

  final SchoolClassService schoolClassService;

  public void execute(SchoolClassId schoolClassId, StudentId studentId) {
    schoolClassService.addStudentToClass(schoolClassId, studentId);
  }

}
