package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  public SchoolClass execute(SchoolClassName schoolClassName) {
    return schoolClassService.persistSchoolClass(schoolClassName);
  }

  /**
   * Создать школьный класс со списком студентов.
   *
   * @param schoolClassName - название школьного класса параллель-литера (11А)
   * @param studentIds      - список идентификаторов студентов
   * @return SchoolClass (созданный)
   */
  public SchoolClass execute(SchoolClassName schoolClassName, List<Long> studentIds)
      throws NoSuchElementException {

    long schoolClassIdValue = schoolClassService
        .persistSchoolClass(schoolClassName)
        .getSchoolClassId()
        .getValue();

    studentIds.forEach(studentId ->
        schoolClassService.addStudentToClass(
            SchoolClassId.of(schoolClassIdValue), StudentId.of(studentId)
        )
    );

    return schoolClassService.getById(schoolClassIdValue);
  }

  /**
   * Создать школьный класс.
   *
   * @param schoolClass - доменная модель школьного класса
   * @return SchoolClass (созданный)
   */
  public SchoolClass execute(SchoolClass schoolClass) {
    return schoolClassService.persistSchoolClass(SchoolClassName.of(
            schoolClass.getSchoolClassName().getParallel(),
            schoolClass.getSchoolClassName().getLitera()
        )
    );
  }

}
