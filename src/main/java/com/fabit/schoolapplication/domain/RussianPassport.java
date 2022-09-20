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

  private RussianPassport(String serial, String number, LocalDate birthday) {
    this.serial = serial;
    this.number = number;
    this.birthday = birthday;
  }

  public static RussianPassport of(String serial, String number, LocalDate birthday) {
    if (isValidPassport(serial, number, birthday)){
      return new RussianPassport(serial, number, birthday);
    } else{
      throw new IllegalArgumentException();
    }
  }

  public static boolean isValidAge(LocalDate birthday) {
    return (LocalDate.now().getYear() - birthday.getYear() >= 14);
  }

  public static boolean isValidPassport(String serial, String number, LocalDate birthday) {
    if (!Pattern.matches("^[0-9]{6}$", number) || !Pattern.matches("^[0-9]{4}$", serial) ||
        !isValidAge(birthday)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return serial + " " + number + " " + birthday;
  }
}