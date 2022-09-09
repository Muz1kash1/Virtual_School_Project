package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class SchoolClassId {
  long schoolClassId;

  private SchoolClassId(long schoolClassId) {
    this.schoolClassId = schoolClassId;
  }

  public static SchoolClassId of(long schoolClassId) {
    return new SchoolClassId(schoolClassId);
  }
}