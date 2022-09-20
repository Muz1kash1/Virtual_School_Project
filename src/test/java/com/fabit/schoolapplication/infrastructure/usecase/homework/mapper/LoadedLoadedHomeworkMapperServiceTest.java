package com.fabit.schoolapplication.infrastructure.usecase.homework.mapper;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.infrastructure.controller.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoadedLoadedHomeworkMapperServiceTest {

  @Autowired
  LoadedHomeworkMapperService loadedHomeworkMapperService;

  @DisplayName("Маппер работает корректно")
  @Test
  void mapToHomeworkCompletionResultTest() {
    LoadedHomeworkDto loadedHomeworkDto = new LoadedHomeworkDto(1l, 1l, "test", 1l);
    LoadedHomework loadedHomework = loadedHomeworkMapperService.mapDtoToHomework(loadedHomeworkDto);
    Assertions.assertNotNull(loadedHomework);
    Assertions.assertEquals(loadedHomeworkDto.getTaskCompletionResult(),
        loadedHomework.getTaskCompletionResult());
    Assertions.assertEquals(loadedHomeworkDto.getStudentId(), loadedHomework.getStudentId().getValue());
    Assertions.assertEquals(loadedHomeworkDto.getHomeworkId(), loadedHomework.getLoadedHomeworkId().getValue());
    Assertions.assertEquals(loadedHomeworkDto.getHomeworkForClassId(),
        loadedHomework.getHomeworkForClassId().getValue());

    LoadedHomeworkEntity loadedHomeworkEntity = loadedHomeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(
        loadedHomework);
    Assertions.assertNotNull(loadedHomeworkEntity);
    Assertions.assertEquals(loadedHomeworkEntity.getTaskCompletionResult(),
        loadedHomework.getTaskCompletionResult());
    Assertions.assertEquals(loadedHomeworkEntity.getHomeworkForClassId(),
        loadedHomeworkDto.getHomeworkForClassId());
    Assertions.assertEquals(
        loadedHomeworkEntity.getStudent(), loadedHomework.getStudentId().getValue());
    Assertions.assertEquals(loadedHomeworkEntity.getId(), loadedHomework.getHomeworkForClassId().getValue());

  }
}
