package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class GetHomeworkForClassTest {

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

  @Autowired
  SchoolClassRepository schoolClassRepository;

  @Autowired
  CreateSchoolClassUseCase createSchoolClassUseCase;

  @BeforeEach
  void cleanBefore() {
    homeworkForClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
  }

  @AfterEach
  void cleanAfter() {
    homeworkForClassRepository.deleteAll();
    schoolClassRepository.deleteAll();
  }

  @Test
  @DisplayName("Получение урока работает корректно")
  void getHomeworkForClassTest() {

    createSchoolClassUseCase.execute(11, "А");

    createHomeworkForClass.execute(
      Discipline.COMPUTING,
      LocalDate.of(2000, 2, 2),
      SchoolClassId.of(schoolClassRepository.findAll().get(0).getId())
    );

    Assertions.assertEquals(
      Discipline.COMPUTING,
      getHomeworkForClass.execute(
        homeworkForClassRepository.findAll().get(0).getId()).getDiscipline()
    );

    Assertions.assertEquals(
      homeworkForClassRepository.findAll().get(0).getHomeworkTask(),
      getHomeworkForClass.execute(
        homeworkForClassRepository.findAll().get(0).getId()).getTask()
    );

    Assertions.assertEquals(
      homeworkForClassRepository.findAll().get(0).getDate(),
      getHomeworkForClass.execute(
        homeworkForClassRepository.findAll().get(0).getId()).getDate()
    );
  }

}
