package com.fabit.schoolapplication.domain.homework;

import lombok.Value;

@Value
public class HomeworkId {
  long value;

  private HomeworkId(long value) {
    this.value = value;
  }
  
  public static HomeworkId of(long value){
    return new HomeworkId(value);
  }
}
