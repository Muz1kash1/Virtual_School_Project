package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;
import java.time.Clock;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Getter
@Value
public class RussianPassport {

  String serial;
  String number;
  LocalDate birthday;
  private static final int MIN_AGE_FOR_PASSPORT = 14;

  private RussianPassport(String serial, String number, LocalDate birthday) {
    this.serial = serial;
    this.number = number;
    this.birthday = birthday;
  }

  /**
   * Factory method - создание RussianPassport.
   *
   * @param serial   - сериный номер паспорта
   * @param number   - номер паспорта
   * @param birthday - день рождения
   * @param clock    - дата и время в которое будет создавать паспорт
   * @return RussianPassport russian passport
   */
  public static RussianPassport of(String serial, String number, LocalDate birthday, Clock clock) {

    if (isValidPassport(serial, number, birthday, clock)) {
      return new RussianPassport(serial, number, birthday);
    } else {
      throw new IllegalArgumentException("Невалидные данные паспорта");
    }

  }

  /**
   * Валидация возраста, нельзя добавлять паспорт студенту младше 14 лет.
   *
   * @param birthday дата рождения
   * @param clock    дата и время в которое идет проверка
   * @return boolean
   */
  public static boolean isValidAge(LocalDate birthday, Clock clock) {

    return (LocalDate.ofInstant(
      clock.instant(),
      clock.getZone()
    ).getYear() - birthday.getYear() >= MIN_AGE_FOR_PASSPORT);
  }

  /**
   * Метод, валидирующий паспорт и возвращающий boolean.
   *
   * @param serial   - серия паспорта
   * @param number   - номер паспорта
   * @param birthday - день рождения
   * @return boolean
   */
  public static boolean isValidPassport(String serial, String number,
                                        LocalDate birthday, Clock clock) {

    return Pattern.matches("^[0-9]{6}$", number)
      && Pattern.matches("^[0-9]{4}$", serial)
      && isValidAge(birthday, clock);
  }

  @Override
  public String toString() {
    return serial + " " + number + " " + birthday;
  }
}
