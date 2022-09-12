package com.fabit.schoolapplication.domain.schedule.aggregate;

import com.fabit.schoolapplication.domain.ScheduleId;
import com.fabit.schoolapplication.domain.SchoolClassId;
import com.fabit.schoolapplication.domain.schedule.value_object.SchoolDay;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@EqualsAndHashCode
public class Schedule {

  private ScheduleId scheduleId;
  private SchoolClassId schoolClassId;
  private List<SchoolDay> schoolDayList;

  /**
   * Создание расписания занятий для определенного класса
   *
   * @param id            уникальный идентификатор расписания
   * @param schoolClassId уникальный идентификатор класса
   * @param schoolDayList список учебных дней с уроками
   */
  public static Schedule of(ScheduleId id, SchoolClassId schoolClassId,
      List<SchoolDay> schoolDayList) {
    Schedule schedule = new Schedule();
    schedule.setScheduleId(id);
    schedule.setSchoolClassId(schoolClassId);
    schedule.setSchoolDayList(schoolDayList);
    return schedule;
  }

  //----------------
  //-Предохранители-
  //----------------
  private void setScheduleId(ScheduleId scheduleId) {
    this.scheduleId = scheduleId;
  }

  private void setSchoolClassId(SchoolClassId schoolClassId) {
    this.schoolClassId = schoolClassId;
  }

  private void setSchoolDayList(
      List<SchoolDay> schoolDayList) {
    this.schoolDayList = schoolDayList;
  }
}
