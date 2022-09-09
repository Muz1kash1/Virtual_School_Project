package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class ScheduleId {
  long scheduleId;

  private ScheduleId(long scheduleId) {
    this.scheduleId = scheduleId;
  }

  public static ScheduleId of(long scheduleId) {
    return new ScheduleId(scheduleId);
  }
}