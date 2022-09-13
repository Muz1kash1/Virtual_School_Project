package com.fabit.schoolapplication.domain.lesson.event;

import com.fabit.schoolapplication.domain.lesson.Lesson;

public class HomeworkTaskSetEvent implements LessonEvent {

  private final Lesson lesson;

  public HomeworkTaskSetEvent(Lesson lesson) {
    this.lesson = lesson;
  }

  @Override
  public Object getContent() {
    return this.lesson;
  }
}
