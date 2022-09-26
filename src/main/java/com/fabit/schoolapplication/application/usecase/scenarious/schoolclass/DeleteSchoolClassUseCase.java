package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  public void execute(int parallel, String litera) {
    schoolClassService.deleteSchoolClass(parallel, litera);
  }

  public void execute(SchoolClass schoolClass) {
    schoolClassService.deleteSchoolClass(schoolClass);
  }

}
