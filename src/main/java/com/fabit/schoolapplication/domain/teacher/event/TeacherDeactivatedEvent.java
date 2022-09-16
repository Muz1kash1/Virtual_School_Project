package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class TeacherDeactivatedEvent implements TeacherEvent {
  private final LocalDate from;
  private final LocalDate to;

  private final TeacherId teacherId;

  public TeacherDeactivatedEvent(LocalDate from, LocalDate to, TeacherId teacherId) {
    this.from = from;
    this.to = to;
    this.teacherId = teacherId;
  }
}
