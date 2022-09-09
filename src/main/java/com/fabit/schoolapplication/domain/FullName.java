package com.fabit.schoolapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import net.sf.saxon.trans.SymbolicName.F;

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
}
