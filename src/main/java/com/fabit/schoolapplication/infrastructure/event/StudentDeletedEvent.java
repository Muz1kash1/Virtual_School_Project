package com.fabit.schoolapplication.infrastructure.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudentDeletedEvent extends ApplicationEvent {

  private final Long studentId;

  /**
   * Событие удаления ученика из БД.
   *
   * @param source    - источник события
   * @param studentId - идентификатор ученика
   */
  public StudentDeletedEvent(Object source, Long studentId) {
    super(source);
    this.studentId = studentId;
  }
}