package com.fabit.schoolapplication.domain.homework.event;

import com.fabit.schoolapplication.domain.homework.Homework;

public class HomeworkCreatedEvent implements HomeworkEvent {

  private final Homework homework;

  public HomeworkCreatedEvent(Homework homework) {
    this.homework = homework;
  }

  @Override
  public Object getContent() {
    return this.homework;
  }
}
