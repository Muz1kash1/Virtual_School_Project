package com.fabit.schoolapplication.domain.schedule.value_object;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.TeacherId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LessonForScheduleTest {

  @Test
  @DisplayName("Создание не валидного, отрицательного, урока по расписанию")
  void ofLessonForScheduleMin() {
    assertThrows(
        IllegalArgumentException.class,
        () -> LessonForSchedule.of(-1, Discipline.ALGEBRA, TeacherId.of(5)),
        "Номер урока должен быть в диапазоне от 1 до 10");
  }

  @Test
  @DisplayName("Создание не валидного, урока (<10) по расписанию")
  void ofLessonForScheduleMax() {
    assertThrows(
        IllegalArgumentException.class,
        () -> LessonForSchedule.of(11, Discipline.ALGEBRA, TeacherId.of(5)),
        "Номер урока должен быть в диапазоне от 1 до 10");
  }
}
