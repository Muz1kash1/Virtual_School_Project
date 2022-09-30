package com.fabit.schoolapplication.domain.journalofstudent.id;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class JournalOfStudentId {
  long longValue;

  private JournalOfStudentId(long academicAchievementOfStudentId) {
    if (academicAchievementOfStudentId > 0) {
      this.longValue = academicAchievementOfStudentId;
    } else {
      throw new IllegalArgumentException("Id не должен быть отрицательным");
    }
  }

  public static JournalOfStudentId of(long academicAchievementOfStudentId) {
    return new JournalOfStudentId(academicAchievementOfStudentId);
  }
}
