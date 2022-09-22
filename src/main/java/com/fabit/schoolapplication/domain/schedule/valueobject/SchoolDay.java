package com.fabit.schoolapplication.domain.schedule.valueobject;

import com.fabit.schoolapplication.domain.schedule.enums.DayOfWeek;
import com.fabit.schoolapplication.domain.schedule.enums.TrainingShift;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@EqualsAndHashCode
@Slf4j
public final class SchoolDay {

  private DayOfWeek dayOfWeek;
  private TrainingShift trainingShift;
  private List<LessonForSchedule> lessonForScheduleList;

  /**
   * Строитель для создания школьного для по расписанию.
   *
   * @param dayOfWeek             - учебный день недели
   * @param trainingShift         - учебная смена занятий
   * @param lessonForScheduleList - список уроков на определенный день недели
   */
  public static SchoolDay of(DayOfWeek dayOfWeek, TrainingShift trainingShift,
      List<LessonForSchedule> lessonForScheduleList) {
    SchoolDay schoolDay = new SchoolDay();
    schoolDay.setDayOfWeek(dayOfWeek);
    schoolDay.setTrainingShift(trainingShift);
    schoolDay.setLessonForScheduleList(lessonForScheduleList);
    return schoolDay;
  }

  //-----------------
  //-Предохранители--
  //-----------------
  private void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  private void setTrainingShift(TrainingShift trainingShift) {
    this.trainingShift = trainingShift;
  }

  private void setLessonForScheduleList(List<LessonForSchedule> lessonForScheduleList) {
    this.lessonForScheduleList = lessonForScheduleList;
  }

}
