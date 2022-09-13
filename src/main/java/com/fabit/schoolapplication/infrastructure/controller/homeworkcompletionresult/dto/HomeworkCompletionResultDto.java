package com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class HomeworkCompletionResultDto implements Serializable {

  private final Long teacherId;
  private final Long studentId;
  private final String taskCompletionResult;
  private final Long lessonId;
}
