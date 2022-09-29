package com.fabit.schoolapplication.domain.academicachevementofstudent.id;

import java.util.UUID;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class AcademicAchievementOfStudentId {

  long longValue;
  UUID uuidValue;

  private AcademicAchievementOfStudentId(long academicAchievementOfStudentId) {

    if (academicAchievementOfStudentId > 0) {
      this.longValue = academicAchievementOfStudentId;
    } else {
      throw new IllegalArgumentException("Id не должен быть отрицательным");
    }

    this.uuidValue = UUID.randomUUID();
  }

  public static AcademicAchievementOfStudentId of(long academicAchievementOfStudentId) {
    return new AcademicAchievementOfStudentId(academicAchievementOfStudentId);
  }

}
