package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  public void execute(SchoolClassName schoolClassName) {
    schoolClassService.deleteSchoolClass(schoolClassName);
  }

  public void execute(SchoolClass schoolClass) {
    schoolClassService.deleteSchoolClass(schoolClass);
  }

}
