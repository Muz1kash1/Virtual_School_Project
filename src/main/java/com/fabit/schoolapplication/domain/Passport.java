package com.fabit.schoolapplication.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class Passport {

  String serial;
  String number;

  private Passport(String serial, String number) {
    this.serial = serial;
    this.number = number;
  }

  public static Passport of(String serial, String number) {
    return new Passport(serial, number);
  }

  public boolean isValidAge(LocalDate birthday) {
    if (LocalDate.now().getYear() - birthday.getYear() >= 14) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return serial + " " + number;
  }
}
