package com.fabit.schoolapplication.domain.loadedhomework;

import lombok.Value;

@Value
public class LoadedHomeworkId {

  long value;

  private LoadedHomeworkId(long value) {
    this.value = value;
  }

  public static LoadedHomeworkId of(long value) {
    return new LoadedHomeworkId(value);
  }
}
