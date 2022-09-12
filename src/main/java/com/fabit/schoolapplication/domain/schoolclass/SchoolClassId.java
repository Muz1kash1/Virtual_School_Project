package com.fabit.schoolapplication.domain.schoolclass;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class SchoolClassId {
  Long value;

  private SchoolClassId(long value) {
    this.value = value;
  }

  public static SchoolClassId of(long value) {
    return new SchoolClassId(value);
  }
}