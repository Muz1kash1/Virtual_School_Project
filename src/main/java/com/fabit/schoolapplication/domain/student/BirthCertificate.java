package com.fabit.schoolapplication.domain.student;

import java.time.LocalDate;
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
    return new BirthCertificate(serial, number, birthday);
  }

  @Override
  public String toString() {
    return serial + " " + number;
  }
}