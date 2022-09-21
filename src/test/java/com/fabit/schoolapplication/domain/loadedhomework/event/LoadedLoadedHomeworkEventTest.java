package com.fabit.schoolapplication.domain.loadedhomework.event;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoadedLoadedHomeworkEventTest {

  @BeforeEach
  void cleanEvents() {
    LoadedHomework.domainEvents.clear();
  }

  @Test
  @DisplayName("создание результата выполнения должно выкидывать соответствующий ивент с соответствующим content")
  void createHomeworkCompletionResultEventTest() {
    LoadedHomework loadedHomework = LoadedHomework.of(
        LoadedHomeworkId.of(1L),
        StudentId.of(1L),
        HomeworkForClassId.of(1L));
    Assertions.assertEquals(1, LoadedHomework.domainEvents.size());
    Assertions.assertEquals(
        loadedHomework,
        LoadedHomework.domainEvents.get(0).getContent());

    LoadedHomework loadedHomework1
        = LoadedHomework.of(
        LoadedHomeworkId.of(2L),
        StudentId.of(2L), HomeworkForClassId.of(2L));
    Assertions.assertEquals(2, LoadedHomework.domainEvents.size());
    Assertions.assertEquals(
        loadedHomework1,
        LoadedHomework.domainEvents.get(1).getContent());
  }

  @Test
  @DisplayName("Изменения результата выполнения должно выкидывать соответствующий ивент с соответствующим content")
  void updateHomeworkCompletionResultEventTest() {
    LoadedHomework loadedHomework = LoadedHomework.of(
        LoadedHomeworkId.of(1L),
        StudentId.of(1L),
        HomeworkForClassId.of(1L));
    loadedHomework.uploadTaskCompletionResult("Test");
    LoadedHomework loadedHomeworkContent = (LoadedHomework) LoadedHomework.domainEvents.get(0).getContent();
    Assertions.assertEquals(2, LoadedHomework.domainEvents.size());
    Assertions.assertEquals("Test", loadedHomeworkContent.getTaskCompletionResult());
  }
}
