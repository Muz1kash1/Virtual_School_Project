package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.Getter;

@Getter
public class TeacherActivatedEvent implements TeacherEvent {
  private final TeacherId teacherId;

  public TeacherActivatedEvent(TeacherId teacherId) {
    this.teacherId = teacherId;
  }
}
