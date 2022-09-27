package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChangeHomeworkTaskTest {

  @Autowired
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;

  @Autowired
  ChangeHomeworkTask changeHomeworkTask;

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
  @DisplayName("Задание задания работает корректно")
  void changeHomeworkForClassTest() {

    createHomeworkForClass.execute(
        Discipline.COMPUTING,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(1L)
    );

    changeHomeworkTask.execute(
        HomeworkForClassId.of(homeworkForClassRepository.findAll().get(0).getId()),
        "test homework"
    );

    Assertions.assertEquals(
        "test homework",
        homeworkForClassRepository.findAll().get(0).getHomeworkTask()
    );

  }
}