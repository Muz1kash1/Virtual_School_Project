package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class LessonId {
  long lessonId;

  private LessonId(long lessonId) {
    this.lessonId = lessonId;
  }

  public static LessonId of(long lessonId) {
    return new LessonId(lessonId);
  }
}