package com.fabit.schoolapplication.domain.journalofstudent.id;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class JournalByDisciplineId {
  long longValue;

  private JournalByDisciplineId(long longValue) {
    if (longValue > 0) {
      this.longValue = longValue;
    } else {
      throw new IllegalArgumentException("Id не должен быть отрицательным");
    }
  }

  public static JournalByDisciplineId of(long longValue) {
    return new JournalByDisciplineId(longValue);
  }

}