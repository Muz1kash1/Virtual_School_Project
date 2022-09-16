package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class FullName {

  String name;
  String surname;
  String patronymic;

  private FullName(String name, String surname, String patronymic) {
    this.name = name;
    this.surname = surname;
    this.patronymic = patronymic;
  }

  public static FullName of(String name, String surname, String patronymic) {
    return new FullName(name, surname, patronymic);
  }

  @Override
  public String toString() {
    return name + " " + surname + " " + patronymic;
  }
}
