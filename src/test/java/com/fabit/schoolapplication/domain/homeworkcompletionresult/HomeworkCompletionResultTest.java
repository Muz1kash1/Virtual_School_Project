package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.StudentId;
import com.fabit.schoolapplication.domain.TeacherId;
import com.fabit.schoolapplication.domain.educatioprogress.Mark;
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
    homeworkCompletionResult.checkHomework(Mark.of("3"));
  }

  @Test
  void uploadCompletedResultTest() {
    Assertions.assertEquals("Test", homeworkCompletionResult.getTaskCompletionResult());
  }

  @Test
  void checkHomeworkTest() {
    Assertions.assertEquals("3",homeworkCompletionResult.getMarkForHomework().getValue());
  }
}
