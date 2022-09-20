package com.fabit.schoolapplication.infrastructure.controller.virtual_school.student.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PassportDto {
  private String serial;
  private String number;
  private LocalDate birthday;

  @Override
  public String toString() {
    return serial + " " + number;
  }
}
