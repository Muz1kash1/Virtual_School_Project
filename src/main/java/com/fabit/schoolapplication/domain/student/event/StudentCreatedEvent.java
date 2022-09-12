package com.fabit.schoolapplication.domain.student.event;

import com.fabit.schoolapplication.domain.student.Student;

public class StudentCreatedEvent implements StudentEvent{
  private final Student student;

  public StudentCreatedEvent(Student student) {
    this.student = student;
  }
}