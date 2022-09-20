package com.fabit.schoolapplication.infrastructure.usecase.homework.mapper;

import com.fabit.schoolapplication.domain.homework.Homework;
import com.fabit.schoolapplication.infrastructure.controller.homework.dto.HomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homework.HomeworkEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HomeworkMapperServiceTest {

  @Autowired
  HomeworkMapperService homeworkMapperService;

  @DisplayName("Маппер работает корректно")
  @Test
  void mapToHomeworkCompletionResultTest() {
    HomeworkDto homeworkDto = new HomeworkDto(1l, 1l, "test", 1l);
    Homework homework = homeworkMapperService.mapDtoToHomework(homeworkDto);
    Assertions.assertNotNull(homework);
    Assertions.assertEquals(homeworkDto.getTaskCompletionResult(),
        homework.getTaskCompletionResult());
    Assertions.assertEquals(homeworkDto.getStudentId(), homework.getStudentId().getValue());
    Assertions.assertEquals(homeworkDto.getHomeworkId(), homework.getHomeworkId().getValue());
    Assertions.assertEquals(homeworkDto.getHomeworkForClassId(),
        homework.getHomeworkForClassId().getValue());

    HomeworkEntity homeworkEntity = homeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(
        homework);
    Assertions.assertNotNull(homeworkEntity);
    Assertions.assertEquals(homeworkEntity.getTaskCompletionResult(),
        homework.getTaskCompletionResult());
    Assertions.assertEquals(homeworkEntity.getHomeworkForClassId(),
        homeworkDto.getHomeworkForClassId());
    Assertions.assertEquals(
        homeworkEntity.getStudent(), homework.getStudentId().getValue());
    Assertions.assertEquals(homeworkEntity.getId(),homework.getHomeworkForClassId().getValue());

  }
}
