package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LessonTest {

  static TeacherId teacherId = TeacherId.of(1);
  static Lesson lesson = Lesson.of(teacherId, Discipline.ALGEBRA);


  @BeforeAll
  static void initializeAll() {
    lesson.setHomeworkText("Тест");
  }


  @Test
  @DisplayName("Присвоение домашке текста задания работает корректно")
  void setHomeworkTextTest() {
    Assertions.assertEquals("Тест", lesson.getHomeworkTask());
    Assertions.assertEquals(Discipline.ALGEBRA,lesson.getDiscipline());
  }
}
