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
  @DisplayName("Удаление школьного класса должно удалять класс и отчислять учеников из него")
  void deleteSchoolClassTest() {
    List<Long> originStudentIds = new ArrayList<>();
    StudentId testStudentId1 = StudentId.of(studentRepository.findAll().get(0).getId());
    StudentId testStudentId2 = StudentId.of(studentRepository.findAll().get(1).getId());
    StudentId testStudentId3 = StudentId.of(studentRepository.findAll().get(2).getId());
    originStudentIds.add(testStudentId1.getValue());
    originStudentIds.add(testStudentId2.getValue());
    originStudentIds.add(testStudentId3.getValue());

    createSchoolClass.execute(11, "А", originStudentIds);
    SchoolClassEntity created = schoolClassRepository.findByParallelAndLitera(11, "А");
    List<StudentInClassEntity> studentsInClass = studentInClassRepository.findAll();

    Assertions.assertNotNull(created);
    Assertions.assertNotNull(studentsInClass);

    deleteSchoolClass.execute(11, "А");
    Assertions.assertNull(schoolClassRepository.findByParallelAndLitera(8, "П"));
    Assertions.assertEquals(0, studentInClassRepository.findAll().size());
  }

}
