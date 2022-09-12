package com.fabit.schoolapplication.domain.schoolclass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolClassNameTest {

  @Test
  @DisplayName("Создание параллели должно создавать корректную параллель")
  void createParallelTest() {
    SchoolClassName schoolClassName = SchoolClassName.of(11, "Б");

    Assertions.assertEquals(11, schoolClassName.getParallel());
    Assertions.assertEquals("Б", schoolClassName.getLitera());
    Assertions.assertEquals("11Б", schoolClassName.getFullName());
  }

  @Test
  @DisplayName("Создание параллели с неверным форматом должно выбрасывать exception")
  void createParallelWithWrongFormatTest() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> SchoolClassName.of(115, "LDAS124"), "Неверный формат параллели");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> SchoolClassName.of(1, "Ъ"), "Неверный формат параллели");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> SchoolClassName.of(12, "А"), "Неверный формат параллели");
  }

}
