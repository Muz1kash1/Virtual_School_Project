package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import static org.mockito.ArgumentMatchers.any;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

  @AfterEach
  void clean() {
    homeworkForClassRepository.deleteAll();
  }

  @DisplayName("Юзкейс создания урока работает корректно")
  @Test
  void createHomeworkForClassTest() {
    createHomeworkForClass.execute(Discipline.COMPUTING, LocalDate.of(2000,2,2));
    Assertions.assertEquals(1, homeworkForClassRepository.findAll().size());
    Assertions.assertNotNull(homeworkForClassRepository.findAll().get(0));
    Assertions.assertEquals(homeworkForClassRepository.findAll().get(0).getDiscipline(),
        Discipline.COMPUTING);
    Assertions.assertEquals( LocalDate.of(2000,2,2),homeworkForClassRepository.findAll().get(0).getDate());


  }

}
