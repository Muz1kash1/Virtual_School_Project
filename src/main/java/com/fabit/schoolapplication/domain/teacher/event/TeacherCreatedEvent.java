package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.Getter;

@Getter
public class TeacherCreatedEvent implements TeacherEvent {
  private final TeacherId teacherId;

  public TeacherCreatedEvent(TeacherId teacherId) {
    this.teacherId = teacherId;
  }
}
