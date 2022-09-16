package com.fabit.schoolapplication.domain.student.event;

import com.fabit.schoolapplication.domain.student.Student;
import lombok.Getter;

@Getter
public class StudentChangedInfoEvent implements StudentDomainEvent {
  private final Student student;

  public StudentChangedInfoEvent(Student student) {
    this.student = student;
  }
}