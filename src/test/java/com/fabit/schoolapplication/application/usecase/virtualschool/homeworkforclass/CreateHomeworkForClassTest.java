package com.fabit.schoolapplication.application.usecase.virtualschool.homeworkforclass;


import com.fabit.schoolapplication.application.usecase.virtualschool.schoolclass.CreateSchoolClass;
import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CreateHomeworkForClassTest {

  @Autowired
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  CreateHomeworkForClass createHomeworkForClass;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @Autowired
  CreateSchoolClass createSchoolClass;

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

    createSchoolClass.execute(11, "А");

    createHomeworkForClass.execute(
        Discipline.COMPUTING,
        LocalDate.of(2000, 2, 2),
        SchoolClassId.of(schoolClassRepository.findAll().get(0).getId())
    );

    Assertions.assertEquals(1, homeworkForClassRepository.findAll().size());
    Assertions.assertNotNull(homeworkForClassRepository.findAll().get(0));
    Assertions.assertEquals(
        homeworkForClassRepository.findAll().get(0).getDiscipline(), Discipline.COMPUTING);
    Assertions.assertEquals(
        LocalDate.of(2000, 2, 2),
        homeworkForClassRepository.findAll().get(0).getDate());
    Assertions.assertEquals(
        homeworkForClassRepository.findAll().get(0).getSchoolClassId(),
        schoolClassRepository.findAll().get(0).getId());
  }

}
