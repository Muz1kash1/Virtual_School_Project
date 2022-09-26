package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.AddStudentToClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
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
public class AddStudentToClassTest {

  @Autowired
  AddStudentToClassUseCase addStudentToSchoolClassUseCase;

  @Autowired
  CreateSchoolClassUseCase createSchoolClassUseCase;

  @Autowired
  SchoolClassRepository schoolClassRepository;

  @Autowired
  StudentInClassRepository studentInClassRepository;

  @Autowired
  StudentRepository studentRepository;

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
  @DisplayName("Добавление ученика в класс должно создавать корректную связь класс-ученик")
  void addStudentToClassTest() {

    SchoolClass createdClass = createSchoolClassUseCase.execute(6, "А");
    StudentId testStudentId = StudentId.of(studentRepository.findAll().get(0).getId());

    addStudentToSchoolClassUseCase
        .execute(SchoolClassId.of(createdClass.getSchoolClassId().getValue()), testStudentId);

    Assertions.assertEquals(
        createdClass.getSchoolClassName().getParallel(),
        schoolClassRepository.findByParallelAndLitera(6, "А").getParallel());
    Assertions.assertEquals(
        createdClass.getSchoolClassName().getLitera(),
        schoolClassRepository.findByParallelAndLitera(6, "А").getLitera());

    StudentInClassEntity studentInClassEntity
        = studentInClassRepository.findByStudentId(testStudentId.getValue());

    Assertions.assertEquals(
        createdClass.getSchoolClassId().getValue(),
        studentInClassEntity.getSchoolClassId());

  }

}
