package com.fabit.schoolapplication.domain.student.event;

import com.fabit.schoolapplication.domain.student.Student;


public class StudentChangedInfoEvent implements StudentEvent {
  private final Student student;

  public StudentChangedInfoEvent(Student student) {
    this.student = student;
  }
}