package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class StudentId {
  long studentId;

  private StudentId(long studentId) {
    this.studentId = studentId;
  }

  public static StudentId of(long studentId) {
    return new StudentId(studentId);
  }
}