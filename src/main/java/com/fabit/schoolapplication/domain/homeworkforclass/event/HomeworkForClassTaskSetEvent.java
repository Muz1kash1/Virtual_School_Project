package com.fabit.schoolapplication.domain.homeworkforclass.event;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;

public class HomeworkForClassTaskSetEvent implements HomeworkForClassEvent {

  private final HomeworkForClass homeworkForClass;

  public HomeworkForClassTaskSetEvent(HomeworkForClass homeworkForClass) {
    this.homeworkForClass = homeworkForClass;
  }

  @Override
  public Object getContent() {
    return this.homeworkForClass;
  }

}
