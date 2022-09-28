package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  /**
   * Удалить школьный класс.
   *
   * @param schoolClassName - название школьного класса
   */
  public void execute(SchoolClassName schoolClassName) {
    schoolClassService.deleteSchoolClass(schoolClassName);
  }

  /**
   * Удалить школьный класс.
   *
   * @param schoolClass - доменная модель школьного класса
   */
  public void execute(SchoolClass schoolClass) {
    schoolClassService.deleteSchoolClass(schoolClass);
  }

}
