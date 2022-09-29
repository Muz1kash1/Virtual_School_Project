package com.fabit.schoolapplication.domain.homeworkforclass.event;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;

public class HomeworkForClassCreatedEvent implements HomeworkForClassEvent {

  private final HomeworkForClass homeworkForClass;

  public HomeworkForClassCreatedEvent(HomeworkForClass homeworkForClass) {
    this.homeworkForClass = homeworkForClass;
  }

  @Override
  public Object getContent() {
    return this.homeworkForClass;
  }

}
