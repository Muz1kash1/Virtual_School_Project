package com.fabit.schoolapplication.domain.schedule.aggregate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class SchoolScheduleForDateId {

  private Long id;

  public static SchoolScheduleForDateId of(Long id) {
    SchoolScheduleForDateId schoolScheduleForDateId = new SchoolScheduleForDateId();
    schoolScheduleForDateId.setId(id);
    return schoolScheduleForDateId;
  }

  private void setId(Long id) {
    this.id = id;
  }
}
