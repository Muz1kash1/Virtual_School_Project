package com.fabit.schoolapplication.infrastructure.controller.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto {
  String serial;
  String number;

  public String getSerialAndNumber() {
    return serial + " " + number;
  }
}
