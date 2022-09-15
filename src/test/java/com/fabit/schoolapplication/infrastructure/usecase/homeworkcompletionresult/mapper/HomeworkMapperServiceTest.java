package com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.mapper;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult.HomeworkCompletionResultEntity;
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
  void mapToHomeworkCompletionResultTest(){
    HomeworkCompletionResultDto homeworkCompletionResultDto = new HomeworkCompletionResultDto(1l,1l,"test",1l);
    HomeworkCompletionResult homeworkCompletionResult = homeworkMapperService.mapToHomeworkCompletionResult(homeworkCompletionResultDto);
    Assertions.assertNotNull(homeworkCompletionResult);
    Assertions.assertEquals(homeworkCompletionResultDto.getTaskCompletionResult(),homeworkCompletionResult.getTaskCompletionResult());
    Assertions.assertEquals(homeworkCompletionResultDto.getStudentId(),homeworkCompletionResultDto.getStudentId());

    HomeworkCompletionResultEntity homeworkCompletionResultEntity = homeworkMapperService.mapToHomeworkCompletionResultEntity(homeworkCompletionResult);
    Assertions.assertNotNull(homeworkCompletionResultEntity);
    Assertions.assertEquals(homeworkCompletionResultEntity.getTaskCompletionResult(),homeworkCompletionResult.getTaskCompletionResult());
    Assertions.assertEquals(homeworkCompletionResultEntity.getTaskCompletionResult(),homeworkCompletionResultDto.getTaskCompletionResult());
    Assertions.assertEquals(homeworkCompletionResultEntity.getTeacher().getId(),homeworkCompletionResultDto.getTeacherId());
    Assertions.assertEquals(homeworkCompletionResultEntity.getTeacher().getId(),homeworkCompletionResult.getTeacherId().getValue());

  }
}
