package com.fabit.schoolapplication.infrastructure;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolClassEntityTest {

  @Test
  @DisplayName("Создание SchoolClassEntity должно работать корректно")
  void schoolClassFromDomainModelTest() {
    SchoolClass schoolClass
        = SchoolClass.of(SchoolClassId.of(125), SchoolClassName.of(10, "Б"));
    SchoolClassEntity schoolClassEntity
        = SchoolClassEntity.of(schoolClass, schoolClass.getSchoolClassId().getValue());

    Assertions.assertEquals(125L, schoolClassEntity.getId());
    Assertions.assertEquals(10, schoolClassEntity.getParallel());
    Assertions.assertEquals("Б", schoolClassEntity.getLitera());
  }

  @Test
  @DisplayName("Создание SchoolClassEntity с отрицательным ID должно выбрасывать exception")
  void negativeIdTest() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> SchoolClassEntity.of(
        SchoolClass.of(SchoolClassId.of(15), SchoolClassName.of(11, "А")),
        -15L),
        "Id должен быть больше 0");
  }

}
