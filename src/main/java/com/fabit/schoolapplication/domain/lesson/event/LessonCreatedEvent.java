package com.fabit.schoolapplication.domain.lesson.event;

import com.fabit.schoolapplication.domain.lesson.Lesson;

public class LessonCreatedEvent implements LessonEvent {

  private final Lesson lesson;

  public LessonCreatedEvent(Lesson lesson) {
    this.lesson = lesson;
  }

}
