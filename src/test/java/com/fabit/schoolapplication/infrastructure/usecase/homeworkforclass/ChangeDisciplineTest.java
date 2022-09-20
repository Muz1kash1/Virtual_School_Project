package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.educatioprogress.LessonId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChangeDisciplineTest {

  @Autowired
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  ChangeDiscipline changeDiscipline;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;
  @BeforeEach
  void cleanBefore() {
    homeworkForClassRepository.deleteAll();
  }

  @AfterEach
  void cleanAfter() {
    homeworkForClassRepository.deleteAll();
  }

  @Test
  @DisplayName("Замена предмета работает корректно")
  void changeDisciplineTest() {

    createHomeworkForClass.execute(Discipline.COMPUTING, LocalDate.of(2000, 2, 2),
        SchoolClassId.of(1L));

    Assertions.assertEquals(Discipline.COMPUTING,
        homeworkForClassRepository.findAll().get(0).getDiscipline());
    changeDiscipline.execute(
        HomeworkForClassId.of(homeworkForClassRepository.findAll().get(0).getId()),
        Discipline.BIOLOGY);
    Assertions.assertEquals(Discipline.BIOLOGY,
        homeworkForClassRepository.findAll().get(0).getDiscipline());
  }

}
