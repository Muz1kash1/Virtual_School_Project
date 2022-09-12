package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.StudentId;
import com.fabit.schoolapplication.domain.TeacherId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HomeworkCompletionResultTest {

  static HomeworkCompletionResult homeworkCompletionResult;

  @BeforeAll
  static void initialize() {
    homeworkCompletionResult = HomeworkCompletionResult.of(TeacherId.of(1),
        StudentId.of(1));
    homeworkCompletionResult.uploadTaskCompletionResult("Test");
  }

  @Test
  void uploadCompletedResultTest() {
    Assertions.assertEquals("Test", homeworkCompletionResult.getTaskCompletionResult());
  }


}
