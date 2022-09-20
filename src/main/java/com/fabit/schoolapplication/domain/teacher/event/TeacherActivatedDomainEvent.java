package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.Getter;

@Getter
public class TeacherActivatedDomainEvent implements TeacherDomainEvent {
  private final TeacherId teacherId;

  public TeacherActivatedDomainEvent(TeacherId teacherId) {
    this.teacherId = teacherId;
  }
}
