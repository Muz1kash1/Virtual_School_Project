package com.fabit.schoolapplication.domain.schedule.entity;

import com.fabit.schoolapplication.domain.schedule.valueobject.LessonInSchedule;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Класс описывающий школьный день для определенного класса.
 */
@Getter
@EqualsAndHashCode
public class LessonsForSchoolClass {
  private static final int MAX_SIZE_LESSONS_IN_DAY = 8;

  private final LessonsForSchoolClassId id;
  private final SchoolClassName schoolClassName;
  private final List<LessonInSchedule> lessonsInScheduleList;

  public LessonsForSchoolClass(LessonsForSchoolClassId id, SchoolClassName schoolClassName,
                               List<LessonInSchedule> lessonsInScheduleList) {
    validateLessonsInScheduleList(lessonsInScheduleList);
    this.id = id;
    this.schoolClassName = schoolClassName;
    this.lessonsInScheduleList = lessonsInScheduleList;
  }

  private void validateLessonsInScheduleList(List<LessonInSchedule> lessonsInScheduleList) {
    // проверка на максимальное количество уроков за 1 день
    if (lessonsInScheduleList.size() >= MAX_SIZE_LESSONS_IN_DAY) {
      throw new RuntimeException("Допустимое число уроков не должно превышать 8");
    }
    // проверка на пустоту в списке уроков на 1 день
    if (lessonsInScheduleList.isEmpty()) {
      throw new RuntimeException("Список уроков не может быть пустым");
    }
  }

}
