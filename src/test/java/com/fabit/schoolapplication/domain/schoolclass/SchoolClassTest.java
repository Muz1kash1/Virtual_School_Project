package com.fabit.schoolapplication.domain.schoolclass;

import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolClassTest {

  @Test
  @DisplayName("Одинаковые школьные классы должны быть равны себе, при различиях id - нет")
  void sameSchoolClassTest() {
    SchoolClass schoolClass1
        = SchoolClass.of(SchoolClassId.of(1L), SchoolClassName.of(1, "А"));
    SchoolClass schoolClass2
        = SchoolClass.of(SchoolClassId.of(1L), SchoolClassName.of(1, "А"));
    Assertions.assertEquals(schoolClass1, schoolClass2);

    SchoolClass schoolClass3
        = SchoolClass.of(SchoolClassId.of(2L), SchoolClassName.of(1, "А"));
    SchoolClass schoolClass4
        = SchoolClass.of(SchoolClassId.of(3L), SchoolClassName.of(1, "А"));
    Assertions.assertNotEquals(schoolClass3, schoolClass4);
  }

  @Test
  @DisplayName("Создание школьного класса только по имени должно создавать корректный класс.")
  void schoolClassOfNameTest() {
    SchoolClass schoolClassByName = SchoolClass.of(SchoolClassName.of(5, "Д"));

    Assertions.assertEquals(5, schoolClassByName.getSchoolClassName().getParallel());
    Assertions.assertEquals("Д", schoolClassByName.getSchoolClassName().getLitera());
    Assertions.assertNull(schoolClassByName.getSchoolClassId());
  }

  @Test
  @DisplayName("Создание школьного класса должно создавать корретный школьный класс")
  void createSchoolClassTest() {
    SchoolClass schoolClass
        = SchoolClass.of(SchoolClassId.of(5L), SchoolClassName.of(11, "Б"));
    schoolClass.addStudent(StudentId.of(1L));
    Assertions.assertEquals(5L, schoolClass.getSchoolClassId().getValue());
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(1L)));
  }

  @Test
  @DisplayName("Добавление/удаление ученика в класс должно добавлять/удалять ученика")
  void addAndRemoveStudentTest() {
    List<StudentId> studentIds = new ArrayList<>();
    studentIds.add(StudentId.of(55L));
    studentIds.add(StudentId.of(105L));

    SchoolClass schoolClass = SchoolClass.of(
        SchoolClassId.of(10L),
        SchoolClassName.of(11, "Б"),
        studentIds
    );

    schoolClass.addStudent(StudentId.of(99L));

    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(55L)));
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(105L)));

    schoolClass.removeStudent(StudentId.of(55L));

    Assertions.assertFalse(schoolClass.getStudents().contains(StudentId.of(55L)));
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(105L)));
  }

  @Test
  @DisplayName("Добавление/удаление нескольких учеников в класс должно добавлять/удалять учеников")
  void addAndRemoveMultipleStudentsTest() {
    SchoolClass schoolClass
        = SchoolClass.of(SchoolClassId.of(10L), SchoolClassName.of(11, "Б"));

    schoolClass.addStudent(
        StudentId.of(1001L), StudentId.of(1002L), StudentId.of(1003L)
    );

    Assertions.assertEquals(3, schoolClass.getStudents().size());
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(1001L)));
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(1002L)));
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(1003L)));

    schoolClass.removeStudent(StudentId.of(1001L), StudentId.of(1002L));

    Assertions.assertEquals(1, schoolClass.getStudents().size());
    Assertions.assertTrue(schoolClass.getStudents().contains(StudentId.of(1003L)));
    Assertions.assertFalse(schoolClass.getStudents().contains(StudentId.of(1001L)));
    Assertions.assertFalse(schoolClass.getStudents().contains(StudentId.of(1002L)));
  }


}
