package com.fabit.schoolapplication.domain;

import java.time.LocalDate;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Value;

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
   * @return RussianPassport
   */
  public static RussianPassport of(String serial, String number, LocalDate birthday) {

    if (isValidPassport(serial, number, birthday)) {
      return new RussianPassport(serial, number, birthday);
    } else {
      throw new IllegalArgumentException();
    }

  }

  public static boolean isValidAge(LocalDate birthday) {

    return (LocalDate.now().getYear() - birthday.getYear() >= MIN_AGE_FOR_PASSPORT);
  }

  /**
   * Метод, валидирующий паспорт и возвращающий boolean.
   *
   * @param serial   - серия паспорта
   * @param number   - номер паспорта
   * @param birthday - день рождения
   * @return boolean
   */
  public static boolean isValidPassport(String serial, String number, LocalDate birthday) {

    return Pattern.matches("^[0-9]{6}$", number)
      && Pattern.matches("^[0-9]{4}$", serial)
      && isValidAge(birthday);
  }

  @Override
  public String toString() {
    return serial + " " + number + " " + birthday;
  }
}
