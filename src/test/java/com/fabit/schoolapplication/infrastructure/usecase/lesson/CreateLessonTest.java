package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.lesson.event.LessonCreatedEvent;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest
public class CreateLessonTest {

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  LessonRepository lessonRepository;
  @Autowired
  CreateLesson createLesson;

  @Autowired
  LessonMapperService lessonMapperService;

  @AfterEach
  void clean() {
    lessonRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @DisplayName("Юзкейс создания урока работает корректно")
  @Test
  void createLessonTest() {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1l);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createLesson.execute(TeacherId.of(teacherRepository.findAll().get(0).getId()),
        Discipline.COMPUTING);

    Assertions.assertEquals(1, lessonRepository.findAll().size());
    Assertions.assertNotNull(lessonRepository.findAll().get(0));
    Assertions.assertEquals(lessonRepository.findAll().get(0).getDiscipline(),Discipline.COMPUTING);
    Assertions.assertEquals("test",lessonRepository.findAll().get(0).getTeacher().getFullName());


  }

}
