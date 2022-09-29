package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ChangeHomeworkTaskUseCaseTest {

  @MockBean
  HomeworkForClassRepository homeworkForClassRepository;
  @Autowired
  ChangeHomeworkTaskUseCase changeHomeworkTaskUseCase;

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

    HomeworkForClassEntity homeworkForClassEntity = new HomeworkForClassEntity();
    homeworkForClassEntity.setDate(LocalDate.of(2000, 2, 2));
    homeworkForClassEntity.setSchoolClassId(SchoolClassId.of(1L).getValue());
    homeworkForClassEntity.setId(1L);
    homeworkForClassEntity.setDiscipline(Discipline.COMPUTING);

    when(homeworkForClassRepository.getReferenceById(1L)).thenReturn(homeworkForClassEntity);

    homeworkForClassRepository.save(homeworkForClassEntity);
    changeHomeworkTaskUseCase.execute(
        HomeworkForClassId.of(1), "test homework"
    );

    Assertions.assertEquals(
        "test homework", homeworkForClassRepository.getReferenceById(1L).getHomeworkTask());
  }
}
