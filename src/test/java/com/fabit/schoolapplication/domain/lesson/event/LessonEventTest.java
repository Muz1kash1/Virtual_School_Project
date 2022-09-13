package com.fabit.schoolapplication.domain.lesson.event;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class LessonEventTest {

  @BeforeEach
  void clearLessonEvents(){
    Lesson.domainEvents.clear();
  }

  @Test
  @DisplayName("Создание урока должно выкидывать верный ивент с правильным content")
  void LessonCreatedEventTest() {
    Lesson lesson = Lesson.of(TeacherId.of(1), Discipline.ALGEBRA);
    Lesson lessonContent = (Lesson) Lesson.domainEvents.get(0).getContent();

    Assertions.assertEquals(1, Lesson.domainEvents.size());
    Assertions.assertEquals(lessonContent, lesson);
    Assertions.assertEquals(
        lesson.getDiscipline(), lessonContent.getDiscipline()
    );
    Assertions.assertEquals(
        lesson.getTeacherId(), lessonContent.getTeacherId()
    );

    Lesson lesson1 =
        Lesson.of(TeacherId.of(2), Discipline.BIOLOGY);
    Lesson lessonContent1 = (Lesson) Lesson.domainEvents.get(1).getContent();

    Assertions.assertEquals(2, Lesson.domainEvents.size());
    Assertions.assertEquals(lessonContent1, lesson1);
    Assertions.assertEquals(
        lesson1.getDiscipline(), lessonContent1.getDiscipline()
    );
    Assertions.assertEquals(
        lesson1.getTeacherId(), lessonContent1.getTeacherId()
    );
  }

  @Test
  @DisplayName("Изменение домашнего задания на урок доложно выкидывать соответствующий ивент с верным content")
  void HomeworkTaskSetEventTest(){
    Lesson lesson = Lesson.of(TeacherId.of(1),Discipline.COMPUTING);
    Lesson lessonContent = (Lesson) Lesson.domainEvents.get(0).getContent();
    lesson.setHomeworkText("Test");

    Assertions.assertEquals(2,Lesson.domainEvents.size());
    Assertions.assertEquals("Test",lessonContent.getHomeworkTask());
  }
}
