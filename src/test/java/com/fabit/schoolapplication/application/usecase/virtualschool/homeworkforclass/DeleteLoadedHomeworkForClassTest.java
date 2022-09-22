package com.fabit.schoolapplication.application.usecase.virtualschool.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteLoadedHomeworkForClassTest {

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @Autowired
  DeleteHomeworkForClass deleteHomeworkForClass;

  @BeforeEach
  void cleanBefore() {
    homeworkForClassRepository.deleteAll();
  }

  @AfterEach
  void cleanAfter() {
    homeworkForClassRepository.deleteAll();
  }

  @Test
  @DisplayName("Удаление урока работает корректно")
  void deleteHomeworkForClassTest() {

    createHomeworkForClass.execute(
        Discipline.COMPUTING,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(1L)
    );

    Assertions.assertEquals(1, homeworkForClassRepository.findAll().size());

    deleteHomeworkForClass.execute(
        HomeworkForClassId.of(homeworkForClassRepository.findAll().get(0).getId())
    );

    Assertions.assertEquals(0, homeworkForClassRepository.findAll().size());
  }

}
