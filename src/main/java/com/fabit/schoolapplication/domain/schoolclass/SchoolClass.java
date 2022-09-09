package com.fabit.schoolapplication.domain.schoolclass;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class SchoolClass {

  private final long id;
  private final Parallel parallel;
  private final Set<Long> studentIds;

  // -------
  // ** Конструкторы

  private SchoolClass(long id, Parallel parallel, Set<Long> studentIds) {
    this.id = id;
    this.parallel = parallel;
    this.studentIds = studentIds;
  }

  private SchoolClass(long id, Parallel parallel) {
    this.id = id;
    this.parallel = parallel;
    this.studentIds = new HashSet<>();
  }

  // -------
  // ** Фабричные методы

  /**
   * Создание экземпляра школьного класса без учеников.
   *
   * @param id       идентификатор школьного класса
   * @param parallel параллель школьного класса
   * @return SchoolClass
   */
  public static SchoolClass create(long id, Parallel parallel) {
    return new SchoolClass(id, parallel);
  }

  /**
   * Создание экземпляра школьного класса с известным множеством учеников.
   *
   * @param id         идентификатор школьного класса
   * @param parallel   параллель школьного класса
   * @param studentIds множество идентификаторов учеников
   * @return SchoolClass
   */
  public static SchoolClass create(long id, Parallel parallel, Set<Long> studentIds) {
    return new SchoolClass(id, parallel, studentIds);
  }

  // -------
  // ** Добавление и удаление учеников в класс

  /**
   * Добавление ID ученика в класс.
   *
   * @param studentId идентификатор ученика
   */
  public void addStudent(long studentId) {
    studentIds.add(studentId);
  }

  /**
   * Добавление нескольких ID учеников в класс.
   *
   * @param otherStudentIds идентификаторы остальных учеников
   */
  public void addStudent(long... otherStudentIds) {
    for (long id : otherStudentIds) {
      studentIds.add(id);
    }
  }

  /**
   * Удаление ID ученика из класса.
   *
   * @param studentId идентификатор ученика
   */
  public void removeStudent(long studentId) {
    studentIds.remove(studentId);
  }

  /**
   * Удаление нескольких ID учеников из класса.
   *
   * @param otherStudentIds идентификатор первого ученика
   */
  public void removeStudent(long... otherStudentIds) {
    for (long id : otherStudentIds) {
      studentIds.remove(id);
    }
  }

  // -------

}
