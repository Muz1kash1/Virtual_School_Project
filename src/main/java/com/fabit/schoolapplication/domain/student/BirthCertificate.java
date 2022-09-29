package com.fabit.schoolapplication.domain.student;

import java.time.Clock;
import java.time.LocalDate;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class BirthCertificate {

  String serial;

  String number;

  LocalDate birthday;
  private static final int MIN_AGE_FOR_STUDENT = 5;

  private BirthCertificate(String serial, String number, LocalDate birthday) {
    this.serial = serial;
    this.number = number;
    this.birthday = birthday;
  }

  /**
   * Factory method - создание BirthCertificate.
   *
   * @param serial   - серийния свидетельства о рождении
   * @param number   - номер свидетельства о рождении
   * @param birthday - день рождения
   * @param clock    - дата и время в которое будет создавать свид. о рожд
   * @return BirthCertificate birth certificate
   */
  public static BirthCertificate of(String serial, String number, LocalDate birthday, Clock clock) {

    if (isValidBirthCertificate(serial, number, birthday, clock)) {
      return new BirthCertificate(serial, number, birthday);
    } else {
      throw new IllegalArgumentException("Невалидные данные свид. о рожд.");
    }

  }

  private static boolean isValidBirthCertificate(String serial, String number,
                                                 LocalDate birthday, Clock clock) {

    return Pattern.matches("^[0-9]{6}$", number)
        && Pattern.matches("^[0-9]{4}$", serial)
        && isValidAge(birthday, clock);
  }

  private static boolean isValidAge(LocalDate birthday, Clock clock) {
    return (LocalDate.ofInstant(
        clock.instant(), clock.getZone()).getYear() - birthday.getYear() >= MIN_AGE_FOR_STUDENT
    );
  }

  @Override
  public String toString() {
    return serial + " " + number + " " + birthday;
  }
}