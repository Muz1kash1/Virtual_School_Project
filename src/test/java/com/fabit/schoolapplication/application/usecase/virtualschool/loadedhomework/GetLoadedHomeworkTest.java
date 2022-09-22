package com.fabit.schoolapplication.application.usecase.virtualschool.loadedhomework;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetLoadedHomeworkTest {
  @Autowired
  GetLoadedHomework getLoadedHomework;
  @Autowired
  LoadedHomeworkRepository loadedHomeworkRepository;
  @Autowired
  StudentRepository studentRepository;
  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;

  @BeforeEach
  void cleanBefore() {
    loadedHomeworkRepository.deleteAll();
    homeworkForClassRepository.deleteAll();
    studentRepository.deleteAll();
  }

  @AfterEach
  void cleanAfter() {
    loadedHomeworkRepository.deleteAll();
    homeworkForClassRepository.deleteAll();
    studentRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение загруженной домашки работает корректно")
  void getHomeworkTest() {
    StudentEntity student = new StudentEntity();
    student.setName("test");
    studentRepository.save(student);

    HomeworkForClassEntity homeworkForClass = new HomeworkForClassEntity();
    homeworkForClass.setHomeworkTask("test");
    homeworkForClass.setDiscipline(Discipline.COMPUTING);
    homeworkForClass.setSchoolClassId(1L);
    homeworkForClass.setId(1L);
    homeworkForClass.setDate(LocalDate.now());
    homeworkForClassRepository.save(homeworkForClass);

    LoadedHomeworkEntity loadedHomeworkEntity = new LoadedHomeworkEntity();
    loadedHomeworkEntity.setStudentId(studentRepository.findAll().get(0).getId());
    loadedHomeworkEntity.setHomeworkForClassId(
        homeworkForClassRepository.findAll().get(0).getId());
    loadedHomeworkEntity.setId(1L);
    loadedHomeworkEntity.setTaskCompletionResult("test");
    loadedHomeworkRepository.save(loadedHomeworkEntity);

    Assertions.assertEquals(
        getLoadedHomework.execute(loadedHomeworkRepository.findAll().get(0).getId()).getStudentId(),
        studentRepository.findAll().get(0).getId());
    Assertions.assertEquals(
        getLoadedHomework.execute(loadedHomeworkRepository.findAll().get(0).getId())
            .getHomeworkForClassId(),
        homeworkForClassRepository.findAll().get(0).getId()
    );
    Assertions.assertEquals(
        "test", getLoadedHomework.execute(loadedHomeworkRepository.findAll().get(0).getId())
            .getTaskCompletionResult()
    );
  }
}
