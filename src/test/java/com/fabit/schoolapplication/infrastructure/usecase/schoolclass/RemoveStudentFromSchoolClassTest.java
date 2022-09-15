package com.fabit.schoolapplication.infrastructure.usecase.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor
@SpringBootTest
public class RemoveStudentFromSchoolClassTest {

  @Autowired
  StudentInClassRepository studentInClassRepository;
  @Autowired
  SchoolClassRepository schoolClassRepository;
  @Autowired
  StudentRepository studentRepository;
  @Autowired
  RemoveStudentFromSchoolClass removeStudentFromSchoolClass;
  @Autowired
  AddStudentToSchoolClass addStudentToSchoolClass;
  @Autowired
  CreateSchoolClass createSchoolClass;

  @BeforeEach
  public void init() {
    for (int i = 0; i < 10; i++) {
      studentRepository.save(new StudentEntity());
    }
  }

  @AfterEach
  public void after() {
    studentInClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
    studentRepository.deleteAll();
  }

  @Test
  @DisplayName("Удаление ученика из класса должно разрывать между ними связь")
  void removeStudentFromSchoolClassTest() {
    SchoolClassEntity createdSchoolClass = createSchoolClass.execute(2, "Г");
    StudentEntity testStudent = studentRepository.findAll().get(0);
    addStudentToSchoolClass.execute(
        SchoolClassId.of(createdSchoolClass.getId()), StudentId.of(testStudent.getId()));

    Assertions.assertEquals(1, studentInClassRepository.findAll().size());

    removeStudentFromSchoolClass.execute(
        SchoolClassId.of(createdSchoolClass.getId()), StudentId.of(testStudent.getId()));

    Assertions.assertEquals(0, studentInClassRepository.findAll().size());
  }
}