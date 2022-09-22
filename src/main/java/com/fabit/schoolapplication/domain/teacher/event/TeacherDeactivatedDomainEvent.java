package com.fabit.schoolapplication.domain.teacher.event;

import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class TeacherDeactivatedDomainEvent implements TeacherDomainEvent {

  private final LocalDate from;
  private final LocalDate to;
  private final TeacherId teacherId;

  /**
   * Деактивация учителя (отпуск, больничный).
   *
   * @param from      - с какой даты
   * @param to        - до какой даты
   * @param teacherId - идентификатор учителя
   */
  public TeacherDeactivatedDomainEvent(LocalDate from, LocalDate to, TeacherId teacherId) {
    this.from = from;
    this.to = to;
    this.teacherId = teacherId;
  }
}
