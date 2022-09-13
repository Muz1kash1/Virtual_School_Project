package com.fabit.schoolapplication.domain.schoolclass.event;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.domain.student.StudentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolClassEventTest {

  @BeforeEach
  void init() {
    SchoolClass.domainEvents.clear();
  }

  @Test
  @DisplayName("Создание школьного класса должно выкидывать соответствующий ивент с верным content")
  void createSchoolClassEventTest() {
    SchoolClass exceptedSchoolClass1
        = SchoolClass.of(SchoolClassId.of(17), SchoolClassName.of(9, "В"));
    SchoolClass schoolClassContent1
        = (SchoolClass) SchoolClass.domainEvents.get(0).getContent();

    Assertions.assertEquals(1, SchoolClass.domainEvents.size());
    Assertions.assertEquals(exceptedSchoolClass1, schoolClassContent1);
    Assertions.assertEquals(
        exceptedSchoolClass1.getSchoolClassName(), schoolClassContent1.getSchoolClassName());

    SchoolClass exceptedSchoolClass2
        = SchoolClass.of(SchoolClassId.of(17), SchoolClassName.of(5, "Ы"));
    SchoolClass schoolClassContent2
        = (SchoolClass) SchoolClass.domainEvents.get(0).getContent();

    Assertions.assertEquals(2, SchoolClass.domainEvents.size());
    Assertions.assertEquals(exceptedSchoolClass2, SchoolClass.domainEvents.get(1).getContent());
    Assertions.assertEquals(
        exceptedSchoolClass1.getSchoolClassName(), schoolClassContent2.getSchoolClassName());
  }

  @Test
  @DisplayName("Добавление и удаление ученика в класс должно вызывать ивент с верным content")
  void SchoolClassAddedStudentEventTest() {
    SchoolClass schoolClass
        = SchoolClass.of(SchoolClassId.of(17), SchoolClassName.of(6, "Б"));
    schoolClass.addStudent(StudentId.of(77L));
    schoolClass.removeStudent(StudentId.of(77L));

    SchoolClass schoolClassContentIndex0
        = (SchoolClass) SchoolClass.domainEvents.get(0).getContent();
    StudentId schoolClassContentIndex2
        = (StudentId) SchoolClass.domainEvents.get(2).getContent();

    Assertions.assertEquals(3, SchoolClass.domainEvents.size());
    Assertions.assertEquals(schoolClass, schoolClassContentIndex0);
    Assertions.assertEquals(StudentId.of(77L), schoolClassContentIndex2);
  }

}
