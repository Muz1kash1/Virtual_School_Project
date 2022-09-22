package com.fabit.schoolapplication.infrastructure.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TeacherDeletedEvent extends ApplicationEvent {

  private final Long teacherId;

  public TeacherDeletedEvent(Object source, Long teacherId) {
    super(source);
    this.teacherId = teacherId;
  }

}
