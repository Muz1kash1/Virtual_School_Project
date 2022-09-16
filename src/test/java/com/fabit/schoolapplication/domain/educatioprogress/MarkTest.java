package com.fabit.schoolapplication.domain.educatioprogress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarkTest {

  @Test
  @DisplayName("Можно создать валидную отметку 5")
  void createMark_5_shouldPass() {
    Mark mark = Mark.of(MarkId.of(1L), EducationProgressId.of(1L), 5);
    assertEquals(5, mark.getValue());
  }

  @Test
  @DisplayName("Нельзя создать невалидную отметку 0")
  void createMark_0_shouldThrowIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> Mark.of(MarkId.of(1L), EducationProgressId.of(1L), 0));
  }
}
