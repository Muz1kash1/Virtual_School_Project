package com.fabit.schoolapplication.domain.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassAddedStudentDomainEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassCreatedDomainEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassDomainEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassRemovedStudentDomainEvent;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SchoolClass {

  private SchoolClassId schoolClassId;
  private final SchoolClassName schoolClassName;
  private final List<StudentId> students;

  // -------
  // ** Ивенты

  public static final List<SchoolClassDomainEvent> DOMAIN_EVENTS = new ArrayList<>();

  void registerEvent(SchoolClassDomainEvent event) {
    DOMAIN_EVENTS.add(event);
  }

  // -------
  // ** Приватные конструкторы

  private SchoolClass(SchoolClassName schoolClassName) {
    this.schoolClassName = schoolClassName;
    this.students = new ArrayList<>();
    registerEvent(new SchoolClassCreatedDomainEvent(this));
  }

  private SchoolClass(SchoolClassId id, SchoolClassName schoolClassName, List<StudentId> students) {
    this.schoolClassId = id;
    this.schoolClassName = schoolClassName;
    this.students = students;
    registerEvent(new SchoolClassCreatedDomainEvent(this));
  }

  private SchoolClass(SchoolClassId id, SchoolClassName schoolClassName) {
    this.schoolClassId = id;
    this.schoolClassName = schoolClassName;
    this.students = new ArrayList<>();
    registerEvent(new SchoolClassCreatedDomainEvent(this));
  }

  // -------
  // ** Фабричные методы

  /**
   * Создание экземпляра школьного класса без учеников и идентификатора.
   *
   * @param schoolClassName - параллель школьного класса
   * @return SchoolClass
   */
  public static SchoolClass of(SchoolClassName schoolClassName) {
    return new SchoolClass(schoolClassName);
  }

  /**
   * Создание экземпляра школьного класса без учеников.
   *
   * @param id              - идентификатор школьного класса
   * @param schoolClassName - параллель школьного класса
   * @return SchoolClass
   */
  public static SchoolClass of(SchoolClassId id, SchoolClassName schoolClassName) {
    return new SchoolClass(id, schoolClassName);
  }

  /**
   * Создание экземпляра школьного класса с известным множеством учеников.
   *
   * @param id              - идентификатор школьного класса
   * @param schoolClassName - параллель школьного класса
   * @param students        - множество идентификаторов учеников
   * @return SchoolClass
   */
  public static SchoolClass of(SchoolClassId id, SchoolClassName schoolClassName,
                               List<StudentId> students) {
    return new SchoolClass(id, schoolClassName, students);
  }

  // -------
  // ** Добавление и удаление учеников в класс

  /**
   * Добавление ученика в класс.
   *
   * @param student - ученик, которого нужно добавить
   */
  public void addStudent(StudentId student) {

    students.add(student);

    registerEvent(new SchoolClassAddedStudentDomainEvent(student));
  }

  /**
   * Добавление нескольких учеников в класс.
   *
   * @param students - ученики
   */
  public void addStudent(StudentId... students) {

    for (StudentId student : students) {
      this.students.add(student);
      registerEvent(new SchoolClassAddedStudentDomainEvent(student));
    }

  }

  /**
   * Удаление ученика из класса.
   *
   * @param student - идентификатор ученика
   */
  public void removeStudent(StudentId student) {

    students.remove(student);

    registerEvent(new SchoolClassRemovedStudentDomainEvent(student));
  }

  /**
   * Удаление нескольких ID учеников из класса.
   *
   * @param students - идентификатор первого ученика
   */
  public void removeStudent(StudentId... students) {

    for (StudentId student : students) {
      this.students.remove(student);
      registerEvent(new SchoolClassRemovedStudentDomainEvent(student));
    }

  }

  // -------

}
