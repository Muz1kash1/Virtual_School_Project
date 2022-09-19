package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.LessonId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import java.time.LocalDate;
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
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  ChangeDiscipline changeDiscipline;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @AfterEach
  void clean() {
    homeworkForClassRepository.deleteAll();
    teacherRepository.deleteAll();
  }

  @Test
  @DisplayName("Замена предмета работает корректно")
  void changeDisciplineTest() {
    TeacherEntity teacher = new TeacherEntity();
    teacher.setId(1L);
    teacher.setFullName("test");
    teacherRepository.save(teacher);
    createHomeworkForClass.execute(Discipline.COMPUTING, LocalDate.of(2000,2,2));

    Assertions.assertEquals(Discipline.COMPUTING,
        homeworkForClassRepository.findAll().get(0).getDiscipline());
    changeDiscipline.execute(LessonId.of(homeworkForClassRepository.findAll().get(0).getId()),
        Discipline.BIOLOGY);
    Assertions.assertEquals(Discipline.BIOLOGY, homeworkForClassRepository.findAll().get(0).getDiscipline());
  }

}
