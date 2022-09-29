package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest
public class CreateHomeworkForClassUseCaseTest {
  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  CreateHomeworkForClassUseCase createHomeworkForClassUseCase;
  @Autowired
  CreateSchoolClassUseCase createSchoolClassUseCase;

  @Autowired
  SchoolClassRepository schoolClassRepository;

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

  @DisplayName("Юзкейс создания урока работает корректно")
  @Test
  void createHomeworkForClassTest() {

    createSchoolClassUseCase.execute(SchoolClassName.of(11, "А"));

    createHomeworkForClassUseCase.execute(
        Discipline.COMPUTING,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(schoolClassRepository.findAll().get(0).getId()));

    Assertions.assertEquals(1, homeworkForClassRepository.findAll().size());
    Assertions.assertNotNull(homeworkForClassRepository.findAll().get(0));
    Assertions.assertEquals(
        homeworkForClassRepository.findAll().get(0).getDiscipline(), Discipline.COMPUTING);
    Assertions.assertEquals(
        LocalDate.of(2000, 2, 2), homeworkForClassRepository.findAll().get(0).getDate());
    Assertions.assertEquals(
        homeworkForClassRepository.findAll().get(0).getSchoolClassId(),
        schoolClassRepository.findAll().get(0).getId());
  }
}
