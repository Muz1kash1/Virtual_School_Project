package com.fabit.schoolapplication.domain.schedule.aggregate;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.schedule.entity.LessonsForSchoolClass;
import com.fabit.schoolapplication.domain.schedule.valueobject.LessonInSchedule;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class SchoolScheduleForDate {

  private final SchoolScheduleForDateId schoolScheduleForDateId;
  private final LocalDate localDate;
  private final List<LessonsForSchoolClass> lessonsForSchoolClasses;

  public SchoolScheduleForDate(SchoolScheduleForDateId schoolScheduleForDateId, LocalDate localDate,
                               List<LessonsForSchoolClass> lessonsForSchoolClasses) {
    this.schoolScheduleForDateId = schoolScheduleForDateId;
    this.localDate = localDate;
    this.lessonsForSchoolClasses = lessonsForSchoolClasses;
  }

  /**
   * Выставление урока в расписание.
   * <p>
   * Данный метод позволяет выставить дополнительный урок в расписание
   *
   * @param schoolClassName номер класса (не структурное подразделение)
   * @param lessonNumber    порядковый номер урока
   * @param discipline      наименование дисциплины
   * @param teacherId       уникальный идентификатор преподавателя
   * @return true - урок добавлен в расписание
   */
  public void scheduleLesson(SchoolClassName schoolClassName, int lessonNumber, Discipline discipline,
                             TeacherId teacherId) {
    // нахождение целевого учебного дня с классом
    LessonsForSchoolClass lessons = lessonsForSchoolClasses
      .stream()
      .filter(lessonsForSchoolClass -> schoolClassName.equals(lessonsForSchoolClass.getSchoolClassName()))
      .findFirst()
      .orElse(null);

    // выставление урока в расписание
    if (lessons != null) {
      LessonInSchedule lesson = LessonInSchedule.of(lessonNumber, discipline, teacherId);
      if (!(isLessonFree(lesson) && isLessonNumberFree(lessonNumber, schoolClassName))) {
        lessons.getLessonsInScheduleList().add(lesson);
      }
    }
  }

  /**
   * Отменить урок.
   * <p>
   * Данный метод позволяет отменить урок в расписании
   *
   * @param schoolClassName номер класса (не структурное подразделение)
   * @param lesson          выставленный урок в расписании
   */
  public void cancelLesson(SchoolClassName schoolClassName, LessonInSchedule lesson) {
    // нахождение целевого учебного дня с классом
    LessonsForSchoolClass lessons = lessonsForSchoolClasses
      .stream()
      .filter(lessonsForSchoolClass -> schoolClassName.equals(lessonsForSchoolClass.getSchoolClassName()))
      .findFirst()
      .orElse(null);
    if (lessons != null && lessons.getLessonsInScheduleList().contains(lesson)) {
      lessons.getLessonsInScheduleList().remove(lesson);
    } else {
      throw new RuntimeException("Данного урока не существует");
    }
  }

  /**
   * Данный метод позволяет проверить урок на факт дублирования в других классах, так как на одном уроке, в
   * одно время, может присутствовать только 1 школьный класс.
   *
   * @param lesson проверяемые урок
   * @return true - данный урок выставлен ранее, false - урока не существует
   */
  private boolean isLessonFree(LessonInSchedule lesson) {
    return lessonsForSchoolClasses
      .stream()
      .anyMatch(lessonsForSchoolClass -> lessonsForSchoolClass.getLessonsInScheduleList().contains(lesson));
  }

  /**
   * Данный метод проверяет наличие номера урока в классе. Метод применяется перед выставлением урока в
   * расписание.
   *
   * @param lessonNumber    порядковый номер урока
   * @param schoolClassName номер школьного класса
   */
  private boolean isLessonNumberFree(int lessonNumber, SchoolClassName schoolClassName) {
    return lessonsForSchoolClasses
      .stream()
      .anyMatch(lessonsForSchoolClass -> lessonsForSchoolClass
        .getLessonsInScheduleList()
        .stream()
        .anyMatch(lesson -> lesson.getLessonNumber() == lessonNumber && lessonsForSchoolClass
          .getSchoolClassName()
          .equals(schoolClassName)));
  }
}
