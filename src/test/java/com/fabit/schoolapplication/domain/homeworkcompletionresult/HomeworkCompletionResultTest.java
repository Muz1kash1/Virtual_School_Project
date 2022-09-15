package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HomeworkCompletionResultTest {

  static HomeworkCompletionResult homeworkCompletionResult;


  @BeforeAll
  static void initialize() {
    homeworkCompletionResult = HomeworkCompletionResult.of(TeacherId.of(1),
        StudentId.of(1), LessonId.of(1));
    homeworkCompletionResult.uploadTaskCompletionResult("Test");
  }
  @DisplayName("Загрузка выполнения работает корректно")
  @Test
  void uploadCompletedResultTest() {
    Assertions.assertEquals("Test", homeworkCompletionResult.getTaskCompletionResult());
  }


}
