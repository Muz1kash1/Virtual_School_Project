package com.fabit.schoolapplication.application.usecase.virtual_school.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClass;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor
@SpringBootTest
public class DeleteSchoolClassTest {

  @Autowired
  CreateSchoolClass createSchoolClass;
  @Autowired
  RemoveStudentFromSchoolClass removeStudentFromSchoolClass;
  @Autowired
  SchoolClassRepository schoolClassRepository;
  @Autowired
  StudentInClassRepository studentInClassRepository;
  @Autowired
  DeleteSchoolClass deleteSchoolClass;
  @Autowired
  StudentRepository studentRepository;

  List<Long> originStudentIds;

  @BeforeEach
  public void init() {
    for (int i = 0; i < 10; i++) {
      studentRepository.save(new StudentEntity());
    }
    originStudentIds = new ArrayList<>();
    StudentId testStudentId1 = StudentId.of(studentRepository.findAll().get(0).getId());
    StudentId testStudentId2 = StudentId.of(studentRepository.findAll().get(1).getId());
    StudentId testStudentId3 = StudentId.of(studentRepository.findAll().get(2).getId());
    originStudentIds.add(testStudentId1.getValue());
    originStudentIds.add(testStudentId2.getValue());
    originStudentIds.add(testStudentId3.getValue());
  }

  @AfterEach
  public void after() {
    studentInClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
    studentRepository.deleteAll();
  }

  @Test
  @DisplayName("Удаление школьного класса должно удалять класс и отчислять учеников из него")
  void deleteSchoolClassTest() {
    createSchoolClass.execute(11, "А", originStudentIds);
    SchoolClassEntity created = schoolClassRepository.findByParallelAndLitera(11, "А");
    List<StudentInClassEntity> studentsInClass = studentInClassRepository.findAll();

    Assertions.assertNotNull(created);
    Assertions.assertNotNull(studentsInClass);

    deleteSchoolClass.execute(11, "А");

    Assertions.assertNull(schoolClassRepository.findByParallelAndLitera(11, "А"));
    Assertions.assertEquals(0, studentInClassRepository.findAll().size());
  }

  @Test
  @DisplayName("Удаление школьного класса по агрегату должно удалять класс и отчислять учеников из него")
  void deleteSchoolClassTestWithDomainArg() {
    SchoolClass schoolClass = SchoolClass.of(
        SchoolClassId.of(1L), SchoolClassName.of(9, "Б"));
    schoolClass.addStudent(StudentId.of(originStudentIds.get(0)));
    schoolClass.addStudent(StudentId.of(originStudentIds.get(1)));
    schoolClass.addStudent(StudentId.of(originStudentIds.get(2)));

    createSchoolClass.execute(schoolClass);
    SchoolClassEntity created = schoolClassRepository.findByParallelAndLitera(9, "Б");
    List<StudentInClassEntity> studentsInClass = studentInClassRepository.findAll();

    Assertions.assertNotNull(created);
    Assertions.assertNotNull(studentsInClass);

    deleteSchoolClass.execute(schoolClass);

    Assertions.assertNull(schoolClassRepository.findByParallelAndLitera(9, "Б"));
    Assertions.assertEquals(0, studentInClassRepository.findAll().size());
  }

}
