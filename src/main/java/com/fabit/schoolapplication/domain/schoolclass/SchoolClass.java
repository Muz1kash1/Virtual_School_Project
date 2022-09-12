package com.fabit.schoolapplication.domain.schoolclass;

import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class SchoolClass {

  private final SchoolClassId schoolClassId;
  private final SchoolClassName schoolClassName;
  private final Set<StudentId> students;

  // -------
  // ** Приватные конструкторы

  private SchoolClass(SchoolClassId id, SchoolClassName schoolClassName, Set<StudentId> students) {
    this.schoolClassId = id;
    this.schoolClassName = schoolClassName;
    this.students = students;
  }

  private SchoolClass(SchoolClassId id, SchoolClassName schoolClassName) {
    this.schoolClassId = id;
    this.schoolClassName = schoolClassName;
    this.students = new HashSet<>();
  }

  // -------
  // ** Фабричные методы

  /**
   * Создание экземпляра школьного класса без учеников.
   *
   * @param id              идентификатор школьного класса
   * @param schoolClassName параллель школьного класса
   * @return SchoolClass
   */
  public static SchoolClass of(SchoolClassId id, SchoolClassName schoolClassName) {
    return new SchoolClass(id, schoolClassName);
  }

  /**
   * Создание экземпляра школьного класса с известным множеством учеников.
   *
   * @param id              идентификатор школьного класса
   * @param schoolClassName параллель школьного класса
   * @param students        множество идентификаторов учеников
   * @return SchoolClass
   */
  public static SchoolClass of(
      SchoolClassId id, SchoolClassName schoolClassName, Set<StudentId> students) {

    return new SchoolClass(id, schoolClassName, students);
  }

  // -------
  // ** Добавление и удаление учеников в класс

  /**
   * Добавление ученика в класс
   *
   * @param student ученик, которого нужно добавить
   */
  public void addStudent(StudentId student) {
    students.add(student);
  }

  /**
   * Добавление нескольких учеников в класс.
   *
   * @param students ученики
   */
  public void addStudent(StudentId... students) {
    this.students.addAll(Arrays.asList(students));
  }

  /**
   * Удаление ученика из класса.
   *
   * @param student идентификатор ученика
   */
  public void removeStudent(StudentId student) {
    students.remove(student);
  }

  /**
   * Удаление нескольких ID учеников из класса.
   *
   * @param otherStudents идентификатор первого ученика
   */
  public void removeStudent(StudentId... otherStudents) {
    for (StudentId id : otherStudents) {
      students.remove(id);
    }
  }

  // -------

}
