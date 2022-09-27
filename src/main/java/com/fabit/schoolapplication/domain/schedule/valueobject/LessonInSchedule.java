package com.fabit.schoolapplication.domain.schedule.valueobject;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Класс описывающий урок по расписанию.
 */
@Getter
@EqualsAndHashCode
public final class LessonInSchedule {

  private static final int MAX_LESSON_NUMBER = 10;

  private int lessonNumber;
  private Discipline discipline;
  private TeacherId teacherId;

  private LessonInSchedule() {
  }

  /**
   * Статическая фабрика для создания урока по расписанию.
   *
   * @param lessonNumber порядковый номер урока
   * @param discipline   наименование дисциплины
   * @param teacherId    уникальный идентификатор преподавателя
   */
  public static LessonInSchedule of(int lessonNumber, Discipline discipline, TeacherId teacherId) {
    LessonInSchedule lesson = new LessonInSchedule();
    lesson.setNumber(lessonNumber);
    lesson.setDiscipline(discipline);
    lesson.setTeacherId(teacherId);
    return lesson;
  }

  /**
   * Метод описывающий выставление номера урока.
   *
   * @param lessonNumber порядковый номер урока
   */
  private void setNumber(int lessonNumber) {
    if (!(lessonNumber >= 0 && lessonNumber <= MAX_LESSON_NUMBER)) {
      throw new IllegalArgumentException("Номер урока должен быть в диапазоне от 1 до 10");
    }
    this.lessonNumber = lessonNumber;
  }

  private void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  private void setTeacherId(TeacherId teacherId) {
    this.teacherId = teacherId;
  }
}
