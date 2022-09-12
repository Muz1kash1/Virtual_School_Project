package com.fabit.schoolapplication.domain.teacher;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class TeacherId {
  long value;

  private TeacherId(long value) {
    this.value = value;
  }

  public static TeacherId of(long value) {
    return new TeacherId(value);
  }
}