package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

  private final SchoolClassRepository schoolClassRepository;

  private final StudentInClassRepository studentInClassRepository;

  /**
   * Получить список всех школьных классов.
   *
   * @return List of SchoolClass
   */
  @Override
  @Transactional
  public List<SchoolClass> getAll() {

    List<SchoolClass> schoolClassesToReturn = new ArrayList<>();

    schoolClassRepository
        .findAll()
        .forEach(sc -> schoolClassesToReturn.add(sc.toDomain()));

    if (schoolClassesToReturn.isEmpty()) {
      throw new NoSuchElementException("В БД нет школьных классов");
    }

    return schoolClassesToReturn;
  }

  /**
   * Получить школьный класс по идентификатору.
   *
   * @param id - идентификатор школьного класса
   * @return SchoolClass
   */
  @Override
  public SchoolClass getById(long id) {
    return schoolClassRepository
        .findById(id)
        .orElseThrow(NoSuchElementException::new)
        .toDomain();
  }

  /**
   * Получить школьный класс по названию.
   *
   * @param parallel - параллель (1-11)
   * @param litera   - литера школьного класса (А-Я без ЪЬ)
   * @return SchoolClass
   */
  @Override
  public SchoolClass getByName(int parallel, String litera) {
    return schoolClassRepository
        .findByParallelAndLitera(parallel, litera)
        .toDomain();
  }

  /**
   * Метод, определяющий и выдающий школьный класс по идентификатору ученика.
   *
   * @param studentId - идентификатор ученика
   * @return SchoolClass
   */
  @Override
  public SchoolClass getByStudentId(StudentId studentId) {

    long schoolClassId = studentInClassRepository
        .findByStudentId(studentId.getValue())
        .getSchoolClassId();

    return getById(schoolClassId);
  }

  /**
   * Создать школьный класс по сущности.
   *
   * @param schoolClassEntity - сущность школьного класса
   * @return SchoolClass
   */
  @Override
  public SchoolClass persistSchoolClass(SchoolClassEntity schoolClassEntity) {
    return schoolClassRepository.save(schoolClassEntity).toDomain();
  }

  /**
   * Создать школьный класс по домену.
   *
   * @param schoolClass - школьный класс
   * @return SchoolClass
   */
  @Override
  public SchoolClass persistSchoolClass(SchoolClass schoolClass) {

    SchoolClassEntity savedEntity = schoolClassRepository.save(SchoolClassEntity.of(schoolClass));

    return savedEntity.toDomain();
  }

  /**
   * Удалить школьный класс по параллели и литере.
   *
   * @param parallel - параллель
   * @param litera   - литера
   */
  @Override
  public void deleteSchoolClass(int parallel, String litera) {

    SchoolClassEntity schoolClassEntity
        = schoolClassRepository.findByParallelAndLitera(parallel, litera);

    schoolClassRepository.delete(schoolClassEntity);
  }

  /**
   * Удалить школьный класс по доменному объекту.
   *
   * @param schoolClass - школьный класс
   */
  @Override
  public void deleteSchoolClass(SchoolClass schoolClass) {

    SchoolClassEntity schoolClassEntity = schoolClassRepository.findByParallelAndLitera(
        schoolClass.getSchoolClassName().getParallel(),
        schoolClass.getSchoolClassName().getLitera()
    );

    schoolClassRepository.delete(schoolClassEntity);
  }

  /**
   * Добавить ученика в школьный класс.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика
   */
  @Override
  public void addStudentToClass(SchoolClassId schoolClassId, StudentId studentId) {

    StudentInClassEntity studentInClassEntity
        = StudentInClassEntity.of(schoolClassId.getValue(), studentId.getValue());

    studentInClassRepository.save(studentInClassEntity);
  }

  /**
   * Удалить ученика из школьного класса.
   *
   * @param schoolClassId - идентификатор школьного класса
   * @param studentId     - идентификатор ученика
   */
  @Override
  public void removeStudentFromClass(SchoolClassId schoolClassId, StudentId studentId) {

    StudentInClassEntity studentInClassEntity
        = studentInClassRepository.findByStudentId(studentId.getValue());

    studentInClassRepository.deleteById(studentInClassEntity.getId());
  }
}
