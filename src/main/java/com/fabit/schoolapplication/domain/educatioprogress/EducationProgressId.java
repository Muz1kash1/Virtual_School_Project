package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class EducationProgressId {
  long value;

  private EducationProgressId(long educationProgressId) {
    this.value = educationProgressId;
  }

  public static EducationProgressId of(long educationProgressId) {
    return new EducationProgressId(educationProgressId);
  }

}