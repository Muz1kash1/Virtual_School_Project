package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChangeHomeworkTaskTest {

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  CreateLesson createLesson;

  @Autowired
  LessonRepository lessonRepository;
  @Autowired
  ChangeHomeworkTask changeHomeworkTask;

  @Autowired
  LessonMapperService lessonMapperService;

  @AfterEach
  void clean() {
    lessonRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Задание задания работает корректно")
  void changeHomeworkTaskTest() {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1L);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createLesson.execute(TeacherId.of(teacherRepository.findAll().get(0).getId()),
        Discipline.COMPUTING);

    changeHomeworkTask.execute(LessonId.of(lessonRepository.findAll().get(0).getId()),
        "test homework");
    Assertions.assertEquals("test homework", lessonRepository.findAll().get(0).getHomeworkTask());

  }
}
