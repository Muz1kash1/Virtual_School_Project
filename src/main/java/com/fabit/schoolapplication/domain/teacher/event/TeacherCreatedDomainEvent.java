package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.Getter;

@Getter
public class TeacherCreatedDomainEvent implements TeacherDomainEvent {
  private final TeacherId teacherId;

  public TeacherCreatedDomainEvent(TeacherId teacherId) {
    this.teacherId = teacherId;
  }
}
