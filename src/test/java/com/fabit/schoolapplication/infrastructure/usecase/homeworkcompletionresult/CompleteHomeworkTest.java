package com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkCompletionResultRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.mapper.HomeworkMapperService;
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
  HomeworkCompletionResultRepository homeworkRepository;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  StudentRepository studentRepository;


  @AfterEach
  void cleanall() {
    homeworkRepository.deleteAll();
    homeworkForClassRepository.deleteAll();
    studentRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @DisplayName("тест обновления загруженного дз")
  @Test
  void uploadCompletedHomework() {
    StudentEntity student = new StudentEntity();
    student.setName("test");
    studentRepository.save(student);

    TeacherEntity teacher = new TeacherEntity();
    teacher.setFullName("test");
    teacherRepository.save(teacher);

    HomeworkForClassEntity lesson = new HomeworkForClassEntity();
    lesson.setHomeworkTask("test");
    lesson.setDiscipline(Discipline.COMPUTING);
    homeworkForClassRepository.save(lesson);



    HomeworkCompletionResultDto dto = new HomeworkCompletionResultDto(
        teacherRepository.findAll().get(0).getId(), studentRepository.findAll().get(0).getId(),
        "test", homeworkForClassRepository.findAll().get(0).getId());
    completeHomework.uploadCompletedHomework(dto);
    Assertions.assertNotNull(homeworkRepository.findAll().get(0));
    Assertions.assertEquals(homeworkMapperService.mapToHomeworkCompletionResultEntity(
            homeworkMapperService.mapToHomeworkCompletionResult(dto)).getTaskCompletionResult(),
        homeworkRepository.findAll().get(0).getTaskCompletionResult());
  }

}
