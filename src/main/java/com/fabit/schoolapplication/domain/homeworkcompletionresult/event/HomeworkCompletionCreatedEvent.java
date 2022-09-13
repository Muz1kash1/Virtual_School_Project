package com.fabit.schoolapplication.domain.homeworkcompletionresult.event;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;

public class HomeworkCompletionCreatedEvent implements HomeworkCompletionEvent {

  private final HomeworkCompletionResult homeworkCompletionResult;

  public HomeworkCompletionCreatedEvent(HomeworkCompletionResult homeworkCompletionResult) {
    this.homeworkCompletionResult = homeworkCompletionResult;
  }

  @Override
  public Object getContent() {
    return this.homeworkCompletionResult;
  }
}
