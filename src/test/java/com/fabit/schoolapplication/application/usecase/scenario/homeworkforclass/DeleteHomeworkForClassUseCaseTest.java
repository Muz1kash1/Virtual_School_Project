package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest
public class DeleteHomeworkForClassUseCaseTest {

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  CreateHomeworkForClassUseCase createHomeworkForClassUseCase;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @Autowired
  DeleteHomeworkForClassUseCase deleteHomeworkForClassUseCase;

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

    createHomeworkForClassUseCase.execute(
      Discipline.COMPUTING, LocalDate.of(2000, 2, 2), SchoolClassId.of(1L));

    Assertions.assertEquals(1, homeworkForClassRepository.findAll().size());

    deleteHomeworkForClassUseCase
      .execute(HomeworkForClassId.of(homeworkForClassRepository.findAll().get(0).getId()));

    Assertions.assertEquals(0, homeworkForClassRepository.findAll().size());
  }
}
