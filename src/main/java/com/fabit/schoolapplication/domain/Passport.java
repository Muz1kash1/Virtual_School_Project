package com.fabit.schoolapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
@Value
public class Passport {

  String serial;
  String number;

}
