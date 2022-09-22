package com.fabit.schoolapplication.domain.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoadedHomeworkForClassTest {

  static HomeworkForClass homeworkForClass = HomeworkForClass.of(
      Discipline.ALGEBRA,
      LocalDate.of(2000, 2, 2),
      SchoolClassId.of(1L),
      HomeworkForClassId.of(1L)
  );

  @BeforeAll
  static void initializeAll() {
    homeworkForClass.setHomeworkText("Тест");
  }

  @Test
  @DisplayName("Присвоение домашке текста задания работает корректно")
  void setHomeworkTextTest() {
    Assertions.assertEquals("Тест", homeworkForClass.getTask());
  }
}
