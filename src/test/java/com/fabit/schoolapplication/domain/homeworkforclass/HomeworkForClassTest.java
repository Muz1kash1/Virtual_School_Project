package com.fabit.schoolapplication.domain.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeworkForClassTest {

  static TeacherId teacherId = TeacherId.of(1);
  static HomeworkForClass homeworkForClass = HomeworkForClass.of(Discipline.ALGEBRA, LocalDate.of(2000,2,2));


  @BeforeAll
  static void initializeAll() {
    homeworkForClass.setHomeworkText("Тест");
  }


  @Test
  @DisplayName("Присвоение домашке текста задания работает корректно")
  void setHomeworkTextTest() {
    Assertions.assertEquals("Тест", homeworkForClass.getTask());
    Assertions.assertEquals(Discipline.ALGEBRA, homeworkForClass.getDiscipline());
    Assertions.assertEquals( LocalDate.of(2000,2,2),homeworkForClass.getDate());
  }
}
