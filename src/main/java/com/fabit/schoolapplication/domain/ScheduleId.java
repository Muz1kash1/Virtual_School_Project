package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class ScheduleId {
  long value;

  private ScheduleId(long value) {
    this.value = value;
  }

  public static ScheduleId of(long value) {
    return new ScheduleId(value);
  }
}