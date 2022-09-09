package com.fabit.schoolapplication.domain;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class EducationProgressId {
  long educationProgressId;

  private EducationProgressId(long educationProgressId) {
    this.educationProgressId = educationProgressId;
  }

  public static EducationProgressId of(long educationProgressId) {
    return new EducationProgressId(educationProgressId);
  }
}