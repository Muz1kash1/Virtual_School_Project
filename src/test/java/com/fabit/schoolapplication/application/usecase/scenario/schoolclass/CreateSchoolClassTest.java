package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
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
public class CreateSchoolClassTest {

  @Autowired
  CreateSchoolClassUseCase createSchoolClass;

  @Autowired
  RemoveStudentFromClassUseCase removeStudentFromSchoolClass;

  @Autowired
  SchoolClassRepository schoolClassRepository;

  @Autowired
  StudentInClassRepository studentInClassRepository;

  @Autowired
  StudentRepository studentRepository;

  @BeforeEach
  public void init() {
    schoolClassRepository.deleteAll();
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
  @DisplayName("Создание пустого школьного класса должно сохранять корректно в БД")
  void createEmptySchoolClassTest() {
    createSchoolClass.execute(SchoolClassName.of(1, "А"));
    SchoolClassEntity created = schoolClassRepository.findByParallelAndLitera(1, "А");

    Assertions.assertEquals(1, created.getParallel());
    Assertions.assertEquals("А", created.getLitera());
  }

}
