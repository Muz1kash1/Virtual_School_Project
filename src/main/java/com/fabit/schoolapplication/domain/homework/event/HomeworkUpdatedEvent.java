package com.fabit.schoolapplication.domain.homework.event;

import com.fabit.schoolapplication.domain.homework.Homework;

public class HomeworkUpdatedEvent implements HomeworkEvent {

  private final Homework homework;

  public HomeworkUpdatedEvent(Homework homework) {
    this.homework = homework;
  }

  @Override
  public Object getContent() {
    return this.homework;
  }
}
