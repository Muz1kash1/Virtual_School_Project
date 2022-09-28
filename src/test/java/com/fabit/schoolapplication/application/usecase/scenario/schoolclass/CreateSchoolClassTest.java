package com.fabit.schoolapplication.application.usecase.scenario.schoolclass;

import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
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
  @DisplayName("Создание школьного класса с учениками должно создавать корректный класс")
  void createSchoolClassWithStudentsTest() {
    List<StudentEntity> students = studentRepository.findAll();
    List<Long> studentIds = new ArrayList<>();
    students.forEach(studentEntity -> studentIds.add(studentEntity.getId()));

    // Изначально должен быть пустым, поскольку ученики ещё не распределены.
    Assertions.assertEquals(0, studentInClassRepository.findAll().size());
    // Зачисляем всех имеющихся учеников в новый класс.
    createSchoolClass.execute(SchoolClassName.of(2, "Б"), studentIds);
    // Получаем список объектов, отображающих, в каком классе находится каждый ученик.
    List<StudentInClassEntity> studentInClassList = studentInClassRepository.findAll();

    long createdClassId
        = schoolClassRepository.findByParallelAndLitera(2, "Б").getId();

    Assertions.assertEquals(students.size(), studentInClassList.size());
    Assertions.assertEquals(
        studentInClassRepository.findByStudentId(studentIds.get(0)).getStudentId(),
        students.get(0).getId()
    );

    for (StudentEntity studentEntity : students) {
      Assertions.assertEquals(
          studentInClassRepository.findByStudentId(studentEntity.getId()).getSchoolClassId(),
          createdClassId
      );
    }

  }

  @Test
  @DisplayName("Создание пустого школьного класса должно сохранять корректно в БД")
  void createEmptySchoolClassTest() {
    createSchoolClass.execute(SchoolClassName.of(1, "А"));
    SchoolClassEntity createdClass
        = schoolClassRepository.findByParallelAndLitera(1, "А");

    Assertions.assertEquals(1, createdClass.getParallel());
    Assertions.assertEquals("А", createdClass.getLitera());
  }

}
