package com.fabit.schoolapplication.infrastructure.controller.student.dto;

import lombok.Data;

@Data
public class BirthCertificateDto {
  private String serial;
  private String number;

  @Override
  public String toString() {
    return serial + " " + number;
  }
}