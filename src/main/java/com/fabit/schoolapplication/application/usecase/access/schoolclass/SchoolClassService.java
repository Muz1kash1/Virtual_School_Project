package com.fabit.schoolapplication.application.usecase.access.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

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
  SchoolClass getById(long id) throws NotFoundException;

  /**
   * Получить школьный класс по названию.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера школьного класса (А-Я без ЪЬ)
   * @return SchoolClass
   */
  SchoolClass getByName(int parallel, String litera);

  /**
   * Метод, определяющий и выдающий школьный класс по идентификатору ученика.
   *
   * @param studentId - идентификатор ученика
   * @return SchoolClass
   */
  SchoolClass getByStudentId(StudentId studentId) throws NotFoundException;

  /**
   * Создать школьный класс по сущности.
   *
   * @param schoolClassEntity - сущность школьного класса
   * @return SchoolClass
   */
  SchoolClass persistSchoolClass(SchoolClassEntity schoolClassEntity);

  /**
   * Создать школьный класс по домену.
   *
   * @param schoolClass - школьный класс
   * @return SchoolClass
   */
  SchoolClass persistSchoolClass(SchoolClass schoolClass);

  /**
   * Удалить школьный класс по параллели и литере.
   *
   * @param parallel - параллель
   * @param litera   - литера
   */
  void deleteSchoolClass(int parallel, String litera);

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
