package com.fabit.schoolapplication.infrastructure.usecase.homework;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedLoadedHomeworkCreatedEvent;
import com.fabit.schoolapplication.infrastructure.controller.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homework.mapper.LoadedHomeworkMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompleteHomework {

  @Autowired
  LoadedHomeworkRepository repository;
  @Autowired
  LoadedHomeworkMapperService loadedHomeworkMapperService;

  /**
   * Метод загрузки выполненного дз из дто.
   *
   * @param dto дто
   */
  public void uploadCompletedHomework(LoadedHomeworkDto dto) {
    LoadedHomework loadedHomework = loadedHomeworkMapperService.mapDtoToHomework(dto);
    LoadedHomeworkEntity loadedHomeworkEntity = loadedHomeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(
        loadedHomework);
    repository.save(loadedHomeworkEntity);
  }

  /**
   * Улавливает доменные события создания выполненного дз.
   *
   * @param event доменное событие
   */
  @EventListener
  public void homeworkCompletionCreatedEvent(LoadedLoadedHomeworkCreatedEvent event) {
    LoadedHomework result = (LoadedHomework) event.getContent();
    log.info("HomeworkCompletionCreatedEvent...");
    log.info(
        "Выполнение студентом " + result.getStudentId()
            + " задания " + result.getHomeworkForClassId() + " закончено, результат: "
            + result.getTaskCompletionResult());
  }
}
