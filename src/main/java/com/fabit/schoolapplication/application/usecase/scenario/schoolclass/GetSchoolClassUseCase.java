package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetSchoolClassUseCase {

  final SchoolClassService schoolClassService;

  /**
   * Получить список всех школьных классов.
   *
   * @return List of SchoolClass
   */
  public List<SchoolClass> all() {
    return schoolClassService.getAll();
  }

  /**
   * Получить школьный класс по идентификатору.
   *
   * @param id - идентификатор школьного класса
   * @return SchoolClass
   */
  public SchoolClass byId(long id) {
    return schoolClassService.getById(id);
  }

  /**
   * Получить школьный класс по названию.
   *
   * @param schoolClassName - название класса параллель-литера (11А)
   * @return SchoolClass
   */
  public SchoolClass byName(SchoolClassName schoolClassName) {
    return schoolClassService.getByName(schoolClassName);
  }

  /**
   * Получить школьный класс по идентификатору ученика.
   *
   * @param id - идентификатор ученика
   * @return SchoolClass
   */
  public SchoolClass getByStudentId(long id) {
    try {
      return schoolClassService.getByStudentId(StudentId.of(id));
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException(e);
    }
  }

}
