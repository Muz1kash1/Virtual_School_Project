package com.fabit.schoolapplication.domain.generalvalueobject.discipline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DisciplineTest {

  @Test
  @DisplayName("Парсинг дисциплины которая существует в перечислении "
    + "должен возвращать искомую дисциплину")
  void getTextView() {
    String disciplineTextView = "Литература";
    Discipline discipline = Discipline.parse(disciplineTextView);
    Assertions.assertEquals(disciplineTextView, discipline.getTextView());
  }

  @Test
  @DisplayName("Парсинг дисциплины которой не существует в перечислении "
    + "должен выбрасывать ошибку")
  void getTextView_invalidTextView() {
    String disciplineTextView = "ЛитератураAA";

    assertThrows(
      IllegalArgumentException.class,
      () -> Discipline.parse(disciplineTextView));
  }
}