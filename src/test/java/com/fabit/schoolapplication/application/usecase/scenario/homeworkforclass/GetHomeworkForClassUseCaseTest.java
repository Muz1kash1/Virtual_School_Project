package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import static org.mockito.Mockito.when;

import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassName;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@Slf4j
@SpringBootTest
public class GetHomeworkForClassUseCaseTest {

  @MockBean
  HomeworkForClassRepository homeworkForClassRepository;

  @Autowired
  CreateHomeworkForClassUseCase createHomeworkForClassUseCase;

  @Autowired
  HomeworkForClassMapper homeworkForClassMapper;

  @Autowired
  DeleteHomeworkForClassUseCase deleteHomeworkForClassUseCase;

  @InjectMocks
  @Autowired
  GetHomeworkForClassUseCase getHomeworkForClassUseCase;

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
  @DisplayName("Получение задания на урок работает корректно")
  void getHomeworkForClassTest() {

    createSchoolClassUseCase.execute(SchoolClassName.of(11, "А"));

    HomeworkForClassEntity mockHomework =
        homeworkForClassMapper.mapHomeworkForClassToEntity(
            HomeworkForClass.of(
                Discipline.COMPUTING,
                LocalDate.of(1, 1, 1),
                SchoolClassId.of(1L),
                HomeworkForClassId.of(1L)
            ));
    mockHomework.setHomeworkTask("test");
    when(homeworkForClassRepository.getReferenceById(1L))
        .thenReturn(mockHomework);

    Assertions.assertEquals(
        Discipline.COMPUTING,
        getHomeworkForClassUseCase.execute(HomeworkForClassId.of(1L)).getDiscipline());

    Assertions.assertEquals("test",
        getHomeworkForClassUseCase.execute(HomeworkForClassId.of(1L)).getTask());

    Assertions.assertEquals(
        LocalDate.of(1, 1, 1),
        getHomeworkForClassUseCase.execute(HomeworkForClassId.of(1L)).getDate());
  }
}
