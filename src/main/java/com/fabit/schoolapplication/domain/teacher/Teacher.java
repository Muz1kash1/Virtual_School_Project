package com.fabit.schoolapplication.domain.teacher;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.TeacherId;
import lombok.Getter;

/**
 * Агрегат учителя
 *
 * @author SmirnovMA
 */
@Getter
public class Teacher {
  private TeacherId teacherId;
  /** стаж работы учителя */
  private int standingYears;
  /** Ф.И.О учителя */
  private FullName fullName;
  /** паспорт */
  private Passport passport;
  /** снилс */
  private Snils snils;

  private Teacher() {}

  /**
   * Статическая фабрика по созданию объектов учителя
   *
   * @param teacherId id учителя
   * @param fullName Ф.И.О
   * @param passport Паспорт
   * @param snils СНИЛС
   * @param standingYears стаж работы
   * @return объект учителя
   */
  public static Teacher of(
     TeacherId teacherId, FullName fullName, Passport passport, Snils snils, int standingYears) {
    Teacher teacher = new Teacher();
    teacher.teacherId = teacherId;
    teacher.fullName = fullName;
    teacher.passport = passport;
    teacher.snils = snils;
    teacher.changeStandingYears(standingYears);
    return teacher;
  }
  /**
   * Изменить стаж работы учителя
   *
   * @param value колличество полных лет стажа работы
   */
  public void changeStandingYears(int value) throws IllegalArgumentException {
    this.standingYears = value;
  }
}
