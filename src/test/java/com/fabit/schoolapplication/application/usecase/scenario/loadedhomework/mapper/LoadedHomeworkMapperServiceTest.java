package com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.mapper;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.loadedhomework.dto.LoadedHomeworkDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoadedHomeworkMapperServiceTest {

  @Autowired
  LoadedHomeworkMapperService loadedHomeworkMapperService;

  @Autowired
  LoadedHomeworkRepository loadedHomeworkRepository;

  @Autowired
  StudentRepository studentRepository;


  @DisplayName("Маппер работает корректно")
  @Test
  void mapToHomeworkCompletionResultTest() {

    LoadedHomeworkDto loadedHomeworkDto = new LoadedHomeworkDto(
        1L, 1L, "test", 1L
    );

    LoadedHomework loadedHomework = loadedHomeworkMapperService.mapDtoToHomework(loadedHomeworkDto);

    Assertions.assertNotNull(loadedHomework);
    Assertions.assertEquals(
        loadedHomeworkDto.getTaskCompletionResult(),
        loadedHomework.getTaskCompletionResult()
    );
    Assertions.assertEquals(
        loadedHomeworkDto.getStudentId(),
        loadedHomework.getStudentId().getValue()
    );
    Assertions.assertEquals(
        loadedHomeworkRepository.getNextId() - 1,
        loadedHomework.getLoadedHomeworkId().getValue()
    );
    Assertions.assertEquals(
        loadedHomeworkDto.getHomeworkForClassId(),
        loadedHomework.getHomeworkForClassId().getValue()
    );

    LoadedHomeworkEntity loadedHomeworkEntity
        = loadedHomeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(loadedHomework);

    Assertions.assertNotNull(loadedHomeworkEntity);
    Assertions.assertEquals(
        loadedHomeworkEntity.getTaskCompletionResult(),
        loadedHomework.getTaskCompletionResult()
    );
    Assertions.assertEquals(
        loadedHomeworkEntity.getHomeworkForClassId(),
        loadedHomeworkDto.getHomeworkForClassId()
    );
    Assertions.assertEquals(
        loadedHomeworkEntity.getStudentId(),
        loadedHomework.getStudentId().getValue()
    );
    Assertions.assertEquals(
        loadedHomeworkEntity.getId(),
        loadedHomework.getLoadedHomeworkId().getValue()
    );
  }
}
