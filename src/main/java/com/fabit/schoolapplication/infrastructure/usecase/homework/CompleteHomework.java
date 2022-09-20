package com.fabit.schoolapplication.infrastructure.usecase.homework;

import com.fabit.schoolapplication.domain.homework.Homework;
import com.fabit.schoolapplication.domain.homework.event.HomeworkCreatedEvent;
import com.fabit.schoolapplication.infrastructure.controller.homework.dto.HomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homework.HomeworkEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homework.mapper.HomeworkMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompleteHomework {

  @Autowired
  HomeworkRepository repository;
  @Autowired
  HomeworkMapperService homeworkMapperService;

  /**
   * метод загрузки выполненного дз из дто
   * @param dto дто
   */
  public void uploadCompletedHomework(HomeworkDto dto) {
    Homework homework = homeworkMapperService.mapDtoToHomework(
        dto);
    HomeworkEntity homeworkEntity = homeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(
        homework);
    repository.save(homeworkEntity);
  }

  /**
   * улавливает доменные события создания выполненного дз
   * @param event доменное событие
   */
  @EventListener
  public void homeworkCompletionCreatedEvent(HomeworkCreatedEvent event) {
    Homework result = (Homework) event.getContent();
    log.info("HomeworkCompletionCreatedEvent...");
    log.info(
        "Выполнение студентом " + result.getStudentId()
            + " задания " + result.getHomeworkForClassId() + " закончено, результат: " + result.getTaskCompletionResult());
  }
}
