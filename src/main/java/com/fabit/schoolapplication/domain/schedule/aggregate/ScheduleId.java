package com.fabit.schoolapplication.domain.schedule.aggregate;

import lombok.Getter;

@Getter
public final class ScheduleId {

  private Long scheduleId;

  private ScheduleId() {
  }

  public static ScheduleId of(Long scheduleId) {
    ScheduleId scheduleId1 = new ScheduleId();
    scheduleId1.setScheduleId(scheduleId);
    return scheduleId1;
  }

  private void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }
}