package com.fabit.schoolapplication.infrastructure.ui.controller.student.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class BirthCertificateDto {
  private String serial;
  private String number;
  private LocalDate birthday;

  @Override
  public String toString() {
    return serial + " " + number;
  }
}