package com.fabit.schoolapplication.application.usecase.access.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.List;
import java.util.NoSuchElementException;

public interface SchoolClassService {

  /**
   * Получить список всех школьных классов.
   *
   * @return List of SchoolClass
   */
  List<SchoolClass> getAll();

  /**
   * Получить школьный класс по идентификатору.
   *
   * @param id - идентификатор школьного класса
   * @return SchoolClass
   */
  SchoolClass getById(long id) throws NoSuchElementException;

  /**
   * Получить школьный класс по названию.
   *
   * @param schoolClassName - название класса параллель-литера (11А)
   * @return SchoolClass
   */
  SchoolClass getByName(SchoolClassName schoolClassName);

  /**
   * Метод, определяющий и выдающий школьный класс по идентификатору ученика.
   *
   * @param studentId - идентификатор ученика
   * @return SchoolClass
   */
  SchoolClass getByStudentId(StudentId studentId) throws NoSuchElementException;

  /**
   * Создать школьный класс.
   *
   * @param schoolClassName - название класса параллель-литера (11А)
   */
  SchoolClass persistSchoolClass(SchoolClassName schoolClassName);

  /**
   * Создать школьный класс.
   *
   * @param schoolClassName - название класса параллель-литера (11А)
   */
  SchoolClass persistSchoolClass(SchoolClassName schoolClassName, List<StudentId> studentIds);

  /**
   * Удалить школьный класс.
   *
   * @param schoolClassName - название класса параллель-литера (11А)
   */
  void deleteSchoolClass(SchoolClassName schoolClassName);

  /**
   * Удалить школьный класс по доменному объекту.
   *
   * @param schoolClass - школьный класс
   */
  void deleteSchoolClass(SchoolClass schoolClass);

  /**
   * Добавить ученика в школьный класс.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика
   */
  void addStudentToClass(SchoolClassId schoolClassId, StudentId studentId);

  /**
   * Удалить ученика из школьного класса.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика
   */
  void removeStudentFromClass(SchoolClassId schoolClassId, StudentId studentId);

}
