package com.fabit.schoolapplication.domain.loadedhomework;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoadedHomeworkTest {

  static LoadedHomework loadedHomework;

  @BeforeAll
  static void initialize() {

    loadedHomework = LoadedHomework.of(
        LoadedHomeworkId.of(1L),
        StudentId.of(1L),
        HomeworkForClassId.of(1L)
    );

    loadedHomework.uploadTaskCompletionResult("Test");
  }

  @DisplayName("Загрузка выполнения работает корректно")
  @Test
  void uploadCompletedResultTest() {
    Assertions.assertEquals("Test", loadedHomework.getTaskCompletionResult());
  }

}
