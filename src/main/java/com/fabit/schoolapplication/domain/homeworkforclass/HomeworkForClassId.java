package com.fabit.schoolapplication.domain.homeworkforclass;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class HomeworkForClassId {

  long value;

  private HomeworkForClassId(long value) {
    this.value = value;
  }

  public static HomeworkForClassId of(long value) {
    return new HomeworkForClassId(value);
  }

}
