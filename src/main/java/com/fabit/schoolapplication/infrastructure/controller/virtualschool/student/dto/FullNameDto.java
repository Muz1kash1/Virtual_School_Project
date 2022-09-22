package com.fabit.schoolapplication.infrastructure.controller.virtualschool.student.dto;

import lombok.Data;

@Data
public class FullNameDto {
  private String name;
  private String surname;
  private String patronymic;

  @Override
  public String toString() {
    return name + " " + surname + " " + patronymic;
  }
}
