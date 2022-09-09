package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class StudentId {
  long value;

  private StudentId(long value) {
    this.value = value;
  }

  public static StudentId of(long value) {
    return new StudentId(value);
  }
}