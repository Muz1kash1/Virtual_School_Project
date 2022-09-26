package com.fabit.schoolapplication.application.usecase.scenarious.loadedhomework;

import com.fabit.schoolapplication.application.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
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

  @Autowired
  LoadedHomeworkMapperService loadedHomeworkMapperService;

  @Autowired
  CompleteHomework completeHomework;

  @Autowired
  LoadedHomeworkRepository loadedHomeworkRepository;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  StudentRepository studentRepository;


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

    LoadedHomeworkDto dto = new LoadedHomeworkDto(
        1L,
        studentRepository.findAll().get(0).getId(),
        "Test",
        homeworkForClassRepository.findAll().get(0).getId()
    );

    completeHomework.uploadCompletedHomework(dto);

    String taskCompletionResult
        = loadedHomeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(
            loadedHomeworkMapperService.mapDtoToHomework(dto)).getTaskCompletionResult();

    Assertions.assertNotNull(loadedHomeworkRepository.findAll().get(0));
    Assertions.assertEquals(
        taskCompletionResult,
        loadedHomeworkRepository.findAll().get(0).getTaskCompletionResult());
  }

}
