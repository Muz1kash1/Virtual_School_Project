package com.fabit.schoolapplication.domain.homeworkcompletionresult.event;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeworkCompletionResultEventTest {

  @BeforeEach
  void cleanEvents(){
    HomeworkCompletionResult.domainEvents.clear();
  }

  @Test
  @DisplayName("создание результата выполнения должно выкидывать соответствующий ивент с соответствующим content")
  void createHomeworkCompletionResultEventTest() {
    HomeworkCompletionResult homeworkCompletionResult = HomeworkCompletionResult.of(TeacherId.of(1),
        StudentId.of(1), LessonId.of(1));
    Assertions.assertEquals(1, HomeworkCompletionResult.domainEvents.size());
    Assertions.assertEquals(homeworkCompletionResult,
        HomeworkCompletionResult.domainEvents.get(0).getContent());

    HomeworkCompletionResult homeworkCompletionResult1
        = HomeworkCompletionResult.of(TeacherId.of(2), StudentId.of(2), LessonId.of(2));
    Assertions.assertEquals(2, HomeworkCompletionResult.domainEvents.size());
    Assertions.assertEquals(homeworkCompletionResult1,
        HomeworkCompletionResult.domainEvents.get(1).getContent());
  }

  @Test
  @DisplayName("Изменения результата выполнения должно выкидывать соответствующий ивент с соответствующим content")
  void updateHomeworkCompletionResultEventTest() {
    HomeworkCompletionResult homeworkCompletionResult = HomeworkCompletionResult.of(TeacherId.of(1),
        StudentId.of(1), LessonId.of(1));
    homeworkCompletionResult.uploadTaskCompletionResult("Test");
    HomeworkCompletionResult homeworkCompletionResultContent = (HomeworkCompletionResult) HomeworkCompletionResult.domainEvents.get(0).getContent();
    Assertions.assertEquals(2,HomeworkCompletionResult.domainEvents.size());
    Assertions.assertEquals("Test",homeworkCompletionResultContent.getTaskCompletionResult());
  }
}
