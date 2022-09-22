package com.fabit.schoolapplication.domain.schedule.entity;

import lombok.Getter;

@Getter
public class LessonsForSchoolClassId {

  private Long id;

  public static LessonsForSchoolClassId of(Long id) {
    LessonsForSchoolClassId lessonsForSchoolClassId = new LessonsForSchoolClassId();
    lessonsForSchoolClassId.setId(id);
    return lessonsForSchoolClassId;
  }

  private void setId(Long id) {
    this.id = id;
  }
}
