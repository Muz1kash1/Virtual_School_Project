package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class TeacherId {
  long teacherId;

  private TeacherId(long teacherId) {
    this.teacherId = teacherId;
  }

  public static TeacherId of(long teacherId) {
    return new TeacherId(teacherId);
  }
}