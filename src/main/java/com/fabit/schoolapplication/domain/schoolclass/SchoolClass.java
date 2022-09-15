package com.fabit.schoolapplication.domain.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassAddedStudentEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassCreatedEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassEvent;
import com.fabit.schoolapplication.domain.schoolclass.event.SchoolClassRemovedStudentEvent;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SchoolClass {

  private final SchoolClassId schoolClassId;
  private final SchoolClassName schoolClassName;
  private final List<StudentId> students;

  // -------
  // ** Ивенты

  public static final List<SchoolClassEvent> domainEvents = new ArrayList<>();

  void registerEvent(SchoolClassEvent event) {
    domainEvents.add(event);
  }

  // -------
  // ** Приватные конструкторы

  private SchoolClass(SchoolClassId id, SchoolClassName schoolClassName, List<StudentId> students) {
    this.schoolClassId = id;
    this.schoolClassName = schoolClassName;
    this.students = students;
    registerEvent(new SchoolClassCreatedEvent(this));
  }

  private SchoolClass(SchoolClassId id, SchoolClassName schoolClassName) {
    this.schoolClassId = id;
    this.schoolClassName = schoolClassName;
    this.students = new ArrayList<>();
    registerEvent(new SchoolClassCreatedEvent(this));
  }

  // -------
  // ** Фабричные методы

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
  public static SchoolClass of(
      SchoolClassId id, SchoolClassName schoolClassName, List<StudentId> students) {

    return new SchoolClass(id, schoolClassName, students);
  }

  // -------
  // ** Добавление и удаление учеников в класс

  /**
   * Роспуск школьного класса и отчисление из него всех учеников. Отчисление происходит через
   * внутренние методы для корректного выброса ивентов
   */
  public void disband() {
    for (StudentId student : students) {
      removeStudent(student);
    }
  }

  /**
   * Добавление ученика в класс
   *
   * @param student - ученик, которого нужно добавить
   */
  public void addStudent(StudentId student) {
    students.add(student);
    registerEvent(new SchoolClassAddedStudentEvent(student));
  }

  /**
   * Добавление нескольких учеников в класс.
   *
   * @param students - ученики
   */
  public void addStudent(StudentId... students) {
    for (StudentId student : students) {
      this.students.add(student);
      registerEvent(new SchoolClassAddedStudentEvent(student));
    }
  }

  /**
   * Удаление ученика из класса.
   *
   * @param student - идентификатор ученика
   */
  public void removeStudent(StudentId student) {
    students.remove(student);
    registerEvent(new SchoolClassRemovedStudentEvent(student));
  }

  /**
   * Удаление нескольких ID учеников из класса.
   *
   * @param students - идентификатор первого ученика
   */
  public void removeStudent(StudentId... students) {
    for (StudentId student : students) {
      this.students.remove(student);
      registerEvent(new SchoolClassRemovedStudentEvent(student));
    }
  }

  // -------

}
