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

  public static BirthCertificate of(String serial, String number, LocalDate birthday) {
    if (isValidBirthCertificate(serial, number,birthday)) {
      return new BirthCertificate(serial, number, birthday);
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static boolean isValidBirthCertificate(String serial, String number, LocalDate birthday) {
    if (!Pattern.matches("^[0-9]{6}$", number) || !Pattern.matches("^[0-9]{4}$", serial) ||
        !isValidAge(birthday)) {
      return false;
    }
    return true;
  }

  private static boolean isValidAge(LocalDate birthday) {
    return (LocalDate.now().getYear() - birthday.getYear() >= 5);
  }

  @Override
  public String toString() {
    return serial + " " + number;
  }
}