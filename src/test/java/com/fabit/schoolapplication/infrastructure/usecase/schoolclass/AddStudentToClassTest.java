package com.fabit.schoolapplication.infrastructure.usecase.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
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
  AddStudentToSchoolClass addStudentToSchoolClass;
  @Autowired
  CreateSchoolClass createSchoolClass;
  @Autowired
  SchoolClassRepository schoolClassRepository;
  @Autowired
  StudentInClassRepository studentInClassRepository;
  @Autowired
  StudentRepository studentRepository;

  @BeforeEach
  public void init() {
    studentInClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
    studentRepository.deleteAll();

    for (int i = 0; i < 10; i++) {
      studentRepository.save(new StudentEntity());
    }
  }

  @Test
  @DisplayName("Добавление ученика в класс должно создавать корректную связь класс-ученик")
  void addStudentToClassTest() {
    SchoolClassEntity createdClass = createSchoolClass.execute(6, "А");
    StudentId testStudentId = StudentId.of(studentRepository.findAll().get(0).getId());

    StudentInClassEntity studentInClass
        = addStudentToSchoolClass.execute(SchoolClassId.of(createdClass.getId()), testStudentId);

    Assertions.assertEquals(
        createdClass.getParallel(),
        schoolClassRepository.findByParallelAndLitera(6, "А").getParallel());
    Assertions.assertEquals(
        createdClass.getLitera(),
        schoolClassRepository.findByParallelAndLitera(6, "А").getLitera());

    Assertions.assertEquals(createdClass.getId(), studentInClass.getSchoolClassId());
    Assertions.assertEquals(testStudentId.getValue(), studentInClass.getStudentId());
  }

}
