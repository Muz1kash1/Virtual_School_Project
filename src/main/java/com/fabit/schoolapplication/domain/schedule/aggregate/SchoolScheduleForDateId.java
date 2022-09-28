package com.fabit.schoolapplication.domain.schedule.aggregate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class SchoolScheduleForDateId {

  private Long id;

  /**
   * Factory method для создания объекта this
   * @param id - идентификатор
   * @return SchoolScheduleForDateId
   */
  public static SchoolScheduleForDateId of(Long id) {
    SchoolScheduleForDateId schoolScheduleForDateId = new SchoolScheduleForDateId();
    schoolScheduleForDateId.setId(id);
    return schoolScheduleForDateId;
  }

  private void setId(Long id) {
    this.id = id;
  }
}
