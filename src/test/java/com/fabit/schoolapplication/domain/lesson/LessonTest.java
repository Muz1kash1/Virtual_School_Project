package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.TeacherId;
import com.fabit.schoolapplication.domain.educatioprogress.Mark;

import com.fabit.schoolapplication.domain.StudentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LessonTest {

  static TeacherId teacherId = TeacherId.of(1);
  static Lesson lesson = Lesson.of(teacherId);

  static StudentId studentId = StudentId.of(1);

  @BeforeAll
  static void initializeAll() {
    Mark mark = Mark.of("3");
    lesson.setHomeworkText("Тест");
    lesson.setMarkForLesson(studentId,mark);
  }



  @Test
  @DisplayName("Присвоение домашке текста задания работает корректно")
  void setHomeworkTextTest() {
    Assertions.assertEquals("Тест", lesson.getHomeworkTask());
  }

  @Test
  @DisplayName("Выставление оценки за урок работает корректно")
  void setMarkForLessonTest() {
    Assertions.assertEquals("3",lesson.getMarks().get(studentId).getValue());
  }
}
