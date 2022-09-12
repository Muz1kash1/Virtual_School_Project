package com.fabit.schoolapplication.domain.homeworkcompletionresult.event;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;

public class HomeworkCompletionUpdatedEvent implements HomeworkCompletionEvent {

  private final HomeworkCompletionResult homeworkCompletionResult;

  public HomeworkCompletionUpdatedEvent(HomeworkCompletionResult homeworkCompletionResult) {
    this.homeworkCompletionResult = homeworkCompletionResult;
  }
}
