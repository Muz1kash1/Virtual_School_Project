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
public class ChangeDisciplineTest {
  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  CreateLesson createLesson;

  @Autowired
  LessonRepository lessonRepository;
  @Autowired
  ChangeDiscipline changeDiscipline;

  @Autowired
  LessonMapperService lessonMapperService;

  @AfterEach
  void clean() {
    lessonRepository.deleteAll();
    teacherRepository.deleteAll();
  }
  @Test
  @DisplayName("Замена предмета работает корректно")
  void changeDisciplineTest(){
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1l);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createLesson.execute(TeacherId.of(teacherRepository.findAll().get(0).getId()),
        Discipline.COMPUTING);


    Assertions.assertEquals(Discipline.COMPUTING,lessonRepository.findAll().get(0).getDiscipline());
    changeDiscipline.execute(LessonId.of(lessonRepository.findAll().get(0).getId()),Discipline.BIOLOGY);
    Assertions.assertEquals(Discipline.BIOLOGY,lessonRepository.findAll().get(0).getDiscipline());

  }

}
