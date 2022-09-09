package com.fabit.schoolapplication.domain.lesson;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LessonTest {

  static Lesson lesson = new Lesson();
  static File file = new File("text.txt");

  @BeforeAll
  static void initializeAll() {
    lesson.getHomeworkList().add(Homework.createHomework(1l, null, null));
    lesson.setHomeworkText("Тест");
    lesson.uploadCompletedHomework(1L, file);
  }

  @Test
  @DisplayName("Загрузка домашнего задания работает корректно")
  void uploadHomeworkTest() {
    Assertions.assertEquals(file, lesson.getHomeworkList().get(0).getCompletedHomework());
  }

  @Test
  @DisplayName("Присвоение домашке текста задания работает корректно")
  void setHomeworkTextTest() {
    Assertions.assertEquals("Тест", lesson.getHomeworkTask());
    Assertions.assertEquals("Тест", lesson.getHomeworkList().get(0).getHomeworkTask());
  }
}
