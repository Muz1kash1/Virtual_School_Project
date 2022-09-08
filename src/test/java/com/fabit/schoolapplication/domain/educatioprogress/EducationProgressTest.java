package com.fabit.schoolapplication.domain.educatioprogress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EducationProgressTest {

  @Test
  @DisplayName("Можжно создать валидный объект отметки об успеваемости")
  void create_5_shouldPass() {
    EducationProgress educationProgress = EducationProgress.create(1L, 1L, 1L, 1L, new Mark("5"));
    assertEquals(1L, educationProgress.getEducationProgressId());
  }

  @Test
  @DisplayName("Нельзя создать невалидный объект отметки об успеваемости")
  void create_1_shouldThrowIllegalArgumentException() {
    Throwable thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              EducationProgress educationProgress =
                  EducationProgress.create(1L, 1L, 1L, 1L, new Mark("1"));
            });
  }

  @Test
  @DisplayName("Можно изменить отметку об успеваемости")
  void changeMark_4_shouldPass() {
    EducationProgress educationProgress = EducationProgress.create(1L, 1L, 1L, 1L, new Mark("3"));
    educationProgress.changeMark(new Mark("4"));
    assertEquals("4", educationProgress.getMark().getValue());
  }

  @Test
  @DisplayName("Нельзя изменить отметку об успеваемости на невалидную")
  void changeMark_1_shouldThrowIllegalArgumentException() {
    EducationProgress educationProgress = EducationProgress.create(1L, 1L, 1L, 1L, new Mark("3"));
    Throwable thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              educationProgress.changeMark(new Mark("1"));
            });
  }
}
