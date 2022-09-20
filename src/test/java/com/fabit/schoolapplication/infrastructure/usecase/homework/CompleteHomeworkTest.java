package com.fabit.schoolapplication.infrastructure.usecase.homework;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.infrastructure.controller.homework.dto.HomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homework.mapper.HomeworkMapperService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
public class CompleteHomeworkTest {

  @Autowired
  HomeworkMapperService homeworkMapperService;
  @Autowired
  CompleteHomework completeHomework;
  @Autowired
  HomeworkRepository homeworkRepository;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  StudentRepository studentRepository;


  @AfterEach
  void cleanAll() {
    homeworkRepository.deleteAll();
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



    HomeworkDto dto = new HomeworkDto(
        1L,
        studentRepository.findAll().get(0).getId(),"Test",
        homeworkForClassRepository.findAll().get(0).getId());
    completeHomework.uploadCompletedHomework(dto);
    Assertions.assertNotNull(homeworkRepository.findAll().get(0));
    Assertions.assertEquals(homeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(
            homeworkMapperService.mapDtoToHomework(dto)).getTaskCompletionResult(),
        homeworkRepository.findAll().get(0).getTaskCompletionResult());
  }

}
