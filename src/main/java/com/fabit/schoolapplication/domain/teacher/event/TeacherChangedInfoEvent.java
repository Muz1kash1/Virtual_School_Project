package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import lombok.Getter;

@Getter
public class TeacherChangedInfoEvent implements TeacherEvent {
  private final Teacher teacher;

  public TeacherChangedInfoEvent(Teacher teacher) {
    this.teacher = teacher;
  }
}
