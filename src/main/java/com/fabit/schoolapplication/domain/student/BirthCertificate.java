package com.fabit.schoolapplication.domain.student;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class BirthCertificate {
  String serial;
  String number;

  private BirthCertificate(String serial, String number) {
    this.serial = serial;
    this.number = number;
  }

  public static BirthCertificate of(String serial, String number) {
    return new BirthCertificate(serial, number);
  }
}