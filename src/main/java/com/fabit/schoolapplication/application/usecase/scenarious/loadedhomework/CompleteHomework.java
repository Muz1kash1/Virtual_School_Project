package com.fabit.schoolapplication.application.usecase.scenarious.loadedhomework;

import com.fabit.schoolapplication.application.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedHomeworkCreatedEvent;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompleteHomework {

  final LoadedHomeworkRepository repository;
  final LoadedHomeworkMapperService loadedHomeworkMapperService;

  /**
   * Метод загрузки выполненного ДЗ из ДТО.
   *
   * @param dto дто
   */
  public void uploadCompletedHomework(LoadedHomeworkDto dto) {

    LoadedHomework loadedHomework = loadedHomeworkMapperService.mapDtoToHomework(dto);

    LoadedHomeworkEntity loadedHomeworkEntity
        = loadedHomeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(loadedHomework);

    repository.save(loadedHomeworkEntity);
  }

  /**
   * Улавливает доменные события создания выполненного дз.
   *
   * @param event доменное событие
   */
  @EventListener
  public void homeworkCompletionCreatedEvent(LoadedHomeworkCreatedEvent event) {

    LoadedHomework result = (LoadedHomework) event.getContent();

    log.info("HomeworkCompletionCreatedEvent...");
    log.info(
        "Выполнение студентом " + result.getStudentId()
            + " задания " + result.getHomeworkForClassId() + " закончено, результат: "
            + result.getTaskCompletionResult());
  }
}
