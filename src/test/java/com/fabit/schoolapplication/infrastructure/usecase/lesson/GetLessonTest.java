package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GetLessonTest {

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  LessonRepository lessonRepository;
  @Autowired
  CreateLesson createLesson;

  @Autowired
  LessonMapperService lessonMapperService;

  @Autowired
  DeleteLesson deleteLesson;

  @Autowired
  GetLesson getLesson;

  @AfterEach
  void clean() {
    lessonRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение урока работает корректно")
  void getLessonTest() {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1l);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createLesson.execute(TeacherId.of(teacherRepository.findAll().get(0).getId()),
        Discipline.COMPUTING);

    Assertions.assertEquals(Discipline.COMPUTING,
        getLesson.execute(LessonId.of(lessonRepository.findAll().get(0).getId())).getDiscipline());
    Assertions.assertEquals(teacherRepository.findAll().get(0).getId(),
        getLesson.execute(LessonId.of(lessonRepository.findAll().get(0).getId())).getTeacher()
            .getId());
    Assertions.assertEquals(
        lessonRepository.findAll().get(0).getHomeworkTask(),
        getLesson.execute(LessonId.of(lessonRepository.findAll().get(0).getId())).getHomeworkTask()
    );

  }
}
