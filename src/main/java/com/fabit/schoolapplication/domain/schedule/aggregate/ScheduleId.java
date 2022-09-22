package com.fabit.schoolapplication.domain.schedule.aggregate;

import lombok.Getter;

@Getter
public final class ScheduleId {

  private Long scheduleId;

  private ScheduleId() {
  }

  /**
   * Factory method - создание ScheduleId.
   *
   * @param id - идентификатор расписания
   * @return ScheduleId
   */
  public static ScheduleId of(Long id) {

    ScheduleId scheduleId1 = new ScheduleId();
    scheduleId1.setScheduleId(id);

    return scheduleId1;
  }

  private void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }
}