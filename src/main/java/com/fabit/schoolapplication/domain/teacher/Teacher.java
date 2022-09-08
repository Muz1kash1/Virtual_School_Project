package com.fabit.schoolapplication.domain.teacher;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import lombok.Getter;

/**
 * Агрегат учителя.
 *
 * @author SmirnovMA
 */
@Getter
public class Teacher {

  private long id;

  /**
   * стаж работы учителя.
   */
  private int standingYears;

  /**
   * Ф.И.О учителя.
   */
  private FullName fullName;

  /**
   * паспорт.
   */
  private Passport passport;

  /**
   * снилс.
   */
  private Snils snils;

  private Teacher() {
  }

  /**
   * Статическая фабрика по созданию валидных объектов учителя.
   *
   * @return отвалидированный объект учителя
   */
  public static Teacher create(Long id, FullName fullName, Passport passport, Snils snils,
      int standingYears) {
    Teacher teacher = new Teacher();
    teacher.id = id;
    teacher.fullName = fullName;
    teacher.passport = passport;
    teacher.snils = snils;
    teacher.changeStandingYears(standingYears);
    return teacher;
  }

  /**
   * Изменить стаж работы учителя Стаж не должен быть меньше уже имеющегося Не должен превышать 70
   * лет так как пенсионный возраст не позволяет такого.
   *
   * @param value колличество полных лет стажа работы
   */
  public void changeStandingYears(int value) throws IllegalArgumentException {
    validateStandingYears(value);
    this.standingYears = value;
  }

  /**
   * Валидация стажа работы.
   *
   * @param value стаж работы в годах
   */
  private void validateStandingYears(int value) {
    if (!(value > this.standingYears && value < 70)) {
      throw new IllegalArgumentException(
          "Невалидный стаж работы: стаж не должен быть равен 0,"
              + "меньше имеющегося стажа и больше 70 лет");
    }
  }
}
