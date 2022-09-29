package com.fabit.schoolapplication.domain.student.event;

import com.fabit.schoolapplication.domain.student.Student;
import lombok.Getter;

@Getter
public class StudentCreatedEvent implements StudentDomainEvent {

  private final Student student;

  public StudentCreatedEvent(Student student) {
    this.student = student;
  }
}