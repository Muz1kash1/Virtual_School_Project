package com.fabit.schoolapplication.domain.student;

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
   * @return BirthCertificate
   */
  public static BirthCertificate of(String serial, String number, LocalDate birthday) {
    if (isValidBirthCertificate(serial, number, birthday)) {
      return new BirthCertificate(serial, number, birthday);
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static boolean isValidBirthCertificate(String serial, String number, LocalDate birthday) {
    return Pattern.matches("^[0-9]{6}$", number)
        && Pattern.matches("^[0-9]{4}$", serial)
        && isValidAge(birthday);
  }

  private static boolean isValidAge(LocalDate birthday) {
    return (LocalDate.now().getYear() - birthday.getYear() >= 5);
  }

  @Override
  public String toString() {
    return serial + " " + number;
  }
}