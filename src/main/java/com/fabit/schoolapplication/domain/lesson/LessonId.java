package com.fabit.schoolapplication.domain.lesson;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class LessonId {
  long value;

  private LessonId(long value) {
    this.value = value;
  }

  public static LessonId of(long value) {
    return new LessonId(value);
  }
}