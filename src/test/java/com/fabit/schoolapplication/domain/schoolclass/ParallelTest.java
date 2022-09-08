package com.fabit.schoolapplication.domain.schoolclass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParallelTest {

  @Test
  @DisplayName("Создание параллели должно создавать корректную параллель")
  void createParallelTest() {
    Parallel parallel = Parallel.create(11, "Б");

    Assertions.assertEquals(11, parallel.getGrade());
    Assertions.assertEquals("Б", parallel.getLitera());
    Assertions.assertEquals("11Б", parallel.getTextView());
  }

  @Test
  @DisplayName("Создание параллели с неверным форматом должно выбрасывать exception")
  void createParallelWithWrongFormatTest() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Parallel.create(115, "LDAS124"), "Неверный формат параллели");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Parallel.create(1, "Ъ"), "Неверный формат параллели");
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Parallel.create(12, "А"), "Неверный формат параллели");
  }

}
