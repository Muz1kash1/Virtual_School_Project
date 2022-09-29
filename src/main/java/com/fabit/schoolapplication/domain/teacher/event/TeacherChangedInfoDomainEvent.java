package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import lombok.Getter;

@Getter
public class TeacherChangedInfoDomainEvent implements TeacherDomainEvent {

  private final Teacher teacher;

  public TeacherChangedInfoDomainEvent(Teacher teacher) {
    this.teacher = teacher;
  }

}
