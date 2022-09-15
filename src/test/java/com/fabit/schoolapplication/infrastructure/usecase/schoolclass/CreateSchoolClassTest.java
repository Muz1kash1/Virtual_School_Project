package com.fabit.schoolapplication.infrastructure.usecase.schoolclass;

import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.SchoolClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.schoolclass.StudentInClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
  CreateSchoolClass createSchoolClass;
  @Autowired
  RemoveStudentFromSchoolClass removeStudentFromSchoolClass;
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
  @DisplayName("Создание класса с учениками должно сохранять в БД класс, зачислять в него учеников")
  void createSchoolClassWithStudentsTest() {
    List<Long> originStudentIds = new ArrayList<>();
    StudentId testStudentId1 = StudentId.of(studentRepository.findAll().get(0).getId());
    StudentId testStudentId2 = StudentId.of(studentRepository.findAll().get(1).getId());
    StudentId testStudentId3 = StudentId.of(studentRepository.findAll().get(2).getId());
    originStudentIds.add(testStudentId1.getValue());
    originStudentIds.add(testStudentId2.getValue());
    originStudentIds.add(testStudentId3.getValue());

    createSchoolClass.execute(8, "П", originStudentIds);
    SchoolClassEntity created = schoolClassRepository.findByParallelAndLitera(8, "П");
    List<StudentInClassEntity> studentsInClass = studentInClassRepository.findAll();

    Assertions.assertEquals(8, created.getParallel());
    Assertions.assertEquals("П", created.getLitera());

    Assertions.assertEquals(3, studentsInClass.size());
    Assertions.assertEquals(created.getId(), studentsInClass.get(0).getSchoolClassId());
  }

  @Test
  @DisplayName("Создание пустого школьного класса должно сохранять корректно в БД")
  void createEmptySchoolClassTest() {
    createSchoolClass.execute(1, "А");
    SchoolClassEntity created = schoolClassRepository.findByParallelAndLitera(1, "А");

    Assertions.assertEquals(1, created.getParallel());
    Assertions.assertEquals("А", created.getLitera());
  }

}
