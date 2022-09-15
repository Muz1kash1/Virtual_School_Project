package com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult;

import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkCompletionResultRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.mapper.HomeworkMapperService;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

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
  LessonRepository lessonRepository;
  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  StudentRepository studentRepository;


  @AfterEach
  void cleanall() {
    homeworkRepository.deleteAll();
    lessonRepository.deleteAll();
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

    LessonEntity lesson = new LessonEntity();
    lesson.setHomeworkTask("test");
    lesson.setTeacher(teacher);
    lesson.setDiscipline(Discipline.COMPUTING);
    lessonRepository.save(lesson);



    HomeworkCompletionResultDto dto = new HomeworkCompletionResultDto(
        teacherRepository.findAll().get(0).getId(), studentRepository.findAll().get(0).getId(),
        "test", lessonRepository.findAll().get(0).getId());
    completeHomework.uploadCompletedHomework(dto);
    Assertions.assertNotNull(homeworkRepository.findAll().get(0));
    Assertions.assertEquals(homeworkMapperService.mapToHomeworkCompletionResultEntity(
            homeworkMapperService.mapToHomeworkCompletionResult(dto)).getTaskCompletionResult(),
        homeworkRepository.findAll().get(0).getTaskCompletionResult());
  }

}
