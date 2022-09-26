package com.fabit.schoolapplication.application.usecase.scenarious.schoolclass;

import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.AddStudentToClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenarious.schoolclass.RemoveStudentFromClassUseCase;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
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
  RemoveStudentFromClassUseCase removeStudentFromSchoolClass;

  @Autowired
  AddStudentToClassUseCase addStudentToSchoolClass;

  @Autowired
  CreateSchoolClassUseCase createSchoolClass;

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


  // TODO
  // TODO Починить удаление
  @Test
  @DisplayName("Удаление ученика из класса должно разрывать между ними связь")
  void removeStudentFromSchoolClassTest() {
    SchoolClass schoolClass = createSchoolClass.execute(10, "В");
    StudentEntity student = studentRepository.findAll().get(0);

    Assertions.assertEquals(0, studentInClassRepository.findAll().size());

    addStudentToSchoolClass.execute(
        SchoolClassId.of(schoolClass.getSchoolClassId().getValue()),
        StudentId.of(student.getId())
    );

    Assertions.assertEquals(1, studentInClassRepository.findAll().size());
    Assertions.assertEquals(
        schoolClass.getSchoolClassId().getValue(),
        studentInClassRepository.findByStudentId(student.getId()).getSchoolClassId()
    );

    removeStudentFromSchoolClass.execute(
        SchoolClassId.of(schoolClass.getSchoolClassId().getValue()),
        StudentId.of(student.getId())
    );

    Assertions.assertEquals(0, studentInClassRepository.findAll().size());
  }

}