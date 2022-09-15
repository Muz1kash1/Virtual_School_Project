package com.fabit.schoolapplication.persistence.entity.schoolclass;

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
        = SchoolClassEntity.of(schoolClass);

    Assertions.assertEquals(10, schoolClassEntity.getParallel());
    Assertions.assertEquals("Б", schoolClassEntity.getLitera());
  }

}