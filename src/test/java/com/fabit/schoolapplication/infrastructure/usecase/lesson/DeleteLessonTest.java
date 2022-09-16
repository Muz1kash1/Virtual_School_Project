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
public class DeleteLessonTest {

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

  @AfterEach
  void clean() {
    lessonRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Удаление урока работает корректно")
  void deleteLessonTest() {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1L);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createLesson.execute(TeacherId.of(teacherRepository.findAll().get(0).getId()),
        Discipline.COMPUTING);

    Assertions.assertEquals(1, lessonRepository.findAll().size());

    deleteLesson.execute(LessonId.of(lessonRepository.findAll().get(0).getId()));

    Assertions.assertEquals(0, lessonRepository.findAll().size());
  }

}
