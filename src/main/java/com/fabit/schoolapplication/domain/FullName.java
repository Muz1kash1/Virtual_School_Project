package com.fabit.schoolapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FullName {

  private final String name;
  private final String surname;
  private final String patronymic;

}
