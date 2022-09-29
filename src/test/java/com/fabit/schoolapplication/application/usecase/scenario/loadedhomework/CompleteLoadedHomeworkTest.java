package com.fabit.schoolapplication.application.usecase.scenario.loadedhomework;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public class CompleteLoadedHomeworkTest {

  @Autowired LoadedHomeworkMapperService loadedHomeworkMapperService;

  @Autowired CompleteHomeworkUseCase completeHomeworkUseCase;

  @Autowired LoadedHomeworkRepository loadedHomeworkRepository;

  @Autowired HomeworkForClassRepository homeworkForClassRepository;
  @Autowired StudentRepository studentRepository;

  @AfterEach
  void cleanAfter() {
    loadedHomeworkRepository.deleteAll();
    homeworkForClassRepository.deleteAll();
    studentRepository.deleteAll();
  }

  @DisplayName("тест обновления загруженного дз")
  @Test
  void uploadCompletedHomework() {

    StudentEntity student = new StudentEntity();
    student.setName("test");
    studentRepository.save(student);

    HomeworkForClassEntity homeworkForClass = new HomeworkForClassEntity();
    homeworkForClass.setHomeworkTask("test");
    homeworkForClass.setDiscipline(Discipline.COMPUTING);
    homeworkForClassRepository.save(homeworkForClass);

    LoadedHomework loadedHomework =
      LoadedHomework.of(LoadedHomeworkId.of(1L), StudentId.of(1L), HomeworkForClassId.of(1L));

    completeHomeworkUseCase.uploadCompletedHomework(loadedHomework);

    String taskCompletionResult =
      loadedHomeworkMapperService
        .mapHomeworkToHomeworkCompletionResultEntity(loadedHomework)
        .getTaskCompletionResult();

    Assertions.assertNotNull(loadedHomeworkRepository.findAll().get(0));
    Assertions.assertEquals(
      taskCompletionResult, loadedHomeworkRepository.findAll().get(0).getTaskCompletionResult());
  }
}
