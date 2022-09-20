package com.fabit.schoolapplication.infrastructure.controller.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public final class HomeworkDto implements Serializable {

  @JsonProperty("homeworkId")
  private Long homeworkId;
  @JsonProperty("studentId")
  private Long studentId;
  @JsonProperty("taskCompletionResult")
  private String taskCompletionResult;
  @JsonProperty("homeworkForClassId")
  private Long homeworkForClassId;
}
