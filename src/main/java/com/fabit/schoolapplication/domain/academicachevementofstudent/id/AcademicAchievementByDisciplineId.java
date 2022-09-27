package com.fabit.schoolapplication.domain.academicachevementofstudent.id;

import java.util.UUID;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class AcademicAchievementByDisciplineId {
  long longValue;
  UUID uuidValue;

  private AcademicAchievementByDisciplineId(long longValue) {
    if (longValue > 0) {
      this.longValue = longValue;
    } else {
      throw new IllegalArgumentException("Id не должен быть отрицательным");
    }
    this.uuidValue = UUID.randomUUID();
  }

  public static AcademicAchievementByDisciplineId of(long longValue) {
    return new AcademicAchievementByDisciplineId(longValue);
  }

}