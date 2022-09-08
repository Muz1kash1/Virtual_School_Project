package com.fabit.schoolapplication.domain.schoolclass;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolClassTest {

  @Test
  @DisplayName("Создание школьного класса должно создавать корретный школьный класс")
  void createSchoolClassTest() {
    SchoolClass schoolClass
        = SchoolClass.create(10L, Parallel.create(11, "Б"), Set.of(1L, 5L));
    Assertions.assertEquals(10L, schoolClass.getId());
    Assertions.assertTrue(schoolClass.getStudentIds().contains(1L));
  }

  @Test
  @DisplayName("Добавление/удаление ученика в класс должно добавлять/удалять ученика")
  void addAndRemoveStudentTest() {
    Set<Long> studentIds = new HashSet<>();
    studentIds.add(55L);
    studentIds.add(105L);

    SchoolClass schoolClass
        = SchoolClass.create(10L, Parallel.create(11, "Б"), studentIds);
    schoolClass.addStudent(99L);

    Assertions.assertTrue(schoolClass.getStudentIds().contains(55L));
    Assertions.assertTrue(schoolClass.getStudentIds().contains(105L));

    schoolClass.removeStudent(55L);

    Assertions.assertFalse(schoolClass.getStudentIds().contains(55L));
    Assertions.assertTrue(schoolClass.getStudentIds().contains(105L));
  }

  @Test
  @DisplayName("Добавление/удаление нескольких учеников в класс должно добавлять/удалять учеников")
  void addAndRemoveMultipleStudentsTest() {
    SchoolClass schoolClass
        = SchoolClass.create(10L, Parallel.create(11, "Б"));

    schoolClass.addStudent(1001L, 1002L, 1003L);

    Assertions.assertEquals(3, schoolClass.getStudentIds().size());
    Assertions.assertTrue(schoolClass.getStudentIds().contains(1001L));
    Assertions.assertTrue(schoolClass.getStudentIds().contains(1002L));
    Assertions.assertTrue(schoolClass.getStudentIds().contains(1003L));

    schoolClass.removeStudent(1001L, 1002L);

    Assertions.assertEquals(1, schoolClass.getStudentIds().size());
    Assertions.assertTrue(schoolClass.getStudentIds().contains(1003L));
    Assertions.assertFalse(schoolClass.getStudentIds().contains(1001L));
    Assertions.assertFalse(schoolClass.getStudentIds().contains(1002L));
  }


}
