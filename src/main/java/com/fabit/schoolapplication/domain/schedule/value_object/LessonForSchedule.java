package com.fabit.schoolapplication.domain.schedule.value_object;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@EqualsAndHashCode
@Slf4j
public final class LessonForSchedule {

  private int numberByAccount;
  private Discipline discipline;
  private TeacherId teacherId;

  private LessonForSchedule() {
  }

  /**
   * Строитель для создания урока по расписанию.
   *
   * @param numberByAccount - порядковый номер урока
   * @param discipline      - дисциплина (урок)
   * @param teacherId       - уникальный идентификатор преподавателя
   */
  public static LessonForSchedule of(int numberByAccount, Discipline discipline,
      TeacherId teacherId) {
    LessonForSchedule lessonForSchedule = new LessonForSchedule();
    lessonForSchedule.setNumberByAccount(numberByAccount);
    lessonForSchedule.setDiscipline(discipline);
    lessonForSchedule.setTeacherId(teacherId);
    return lessonForSchedule;
  }

  //----------------
  //-Предохранители-
  //----------------
  private void setNumberByAccount(int numberByAccount) {
    // проверка допустимого диапазона
    if (!(numberByAccount >= 0 && numberByAccount < 10)) {
      throw new IllegalArgumentException("Номер урока должен быть в диапазоне от 1 до 10");
    }
    this.numberByAccount = numberByAccount;
  }

  private void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  private void setTeacherId(TeacherId teacherId) {
    this.teacherId = teacherId;
  }

}
