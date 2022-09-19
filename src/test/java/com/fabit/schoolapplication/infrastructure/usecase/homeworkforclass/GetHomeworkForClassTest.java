package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.LessonId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GetHomeworkForClassTest {

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @Autowired
  DeleteHomeworkForClass deleteHomeworkForClass;

  @Autowired
  GetHomeworkForClass getHomeworkForClass;

  @AfterEach
  void clean() {
    homeworkForClassRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение урока работает корректно")
  void getHomeworkForClassTest() {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1L);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createHomeworkForClass.execute(
        Discipline.COMPUTING, LocalDate.of(2000,2,2));

    Assertions.assertEquals(Discipline.COMPUTING,
        getHomeworkForClass.execute(
            LessonId.of(homeworkForClassRepository.findAll().get(0).getId())).getDiscipline());
    Assertions.assertEquals(
        homeworkForClassRepository.findAll().get(0).getHomeworkTask(),
        getHomeworkForClass.execute(
            LessonId.of(homeworkForClassRepository.findAll().get(0).getId())).getHomeworkTask()
    );
    Assertions.assertEquals(homeworkForClassRepository.findAll().get(0).getDate(),
        getHomeworkForClass.execute(
            LessonId.of(homeworkForClassRepository.findAll().get(0).getId())).getDate()
        );

  }
}
