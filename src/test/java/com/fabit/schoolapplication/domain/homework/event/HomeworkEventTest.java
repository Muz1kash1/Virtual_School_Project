package com.fabit.schoolapplication.domain.homework.event;

import com.fabit.schoolapplication.domain.homework.Homework;
import com.fabit.schoolapplication.domain.educatioprogress.LessonId;
import com.fabit.schoolapplication.domain.homework.HomeworkId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeworkEventTest {

  @BeforeEach
  void cleanEvents() {
    Homework.domainEvents.clear();
  }

  @Test
  @DisplayName("создание результата выполнения должно выкидывать соответствующий ивент с соответствующим content")
  void createHomeworkCompletionResultEventTest() {
    Homework homework = Homework.of(HomeworkId.of(1L),
        StudentId.of(1L), HomeworkForClassId.of(1L));
    Assertions.assertEquals(1, Homework.domainEvents.size());
    Assertions.assertEquals(homework,
        Homework.domainEvents.get(0).getContent());

    Homework homework1
        = Homework.of(HomeworkId.of(2L),
        StudentId.of(2L), HomeworkForClassId.of(2L));
    Assertions.assertEquals(2, Homework.domainEvents.size());
    Assertions.assertEquals(homework1,
        Homework.domainEvents.get(1).getContent());
  }

  @Test
  @DisplayName("Изменения результата выполнения должно выкидывать соответствующий ивент с соответствующим content")
  void updateHomeworkCompletionResultEventTest() {
    Homework homework = Homework.of(HomeworkId.of(1L),
        StudentId.of(1L), HomeworkForClassId.of(1L));
    homework.uploadTaskCompletionResult("Test");
    Homework homeworkContent = (Homework) Homework.domainEvents.get(0).getContent();
    Assertions.assertEquals(2, Homework.domainEvents.size());
    Assertions.assertEquals("Test", homeworkContent.getTaskCompletionResult());
  }
}
