package com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.domain.homeworkcompletionresult.event.HomeworkCompletionCreatedEvent;
import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult.HomeworkCompletionResultEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkCompletionResultRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.mapper.HomeworkMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompleteHomework {

  @Autowired
  HomeworkCompletionResultRepository repository;
  @Autowired
  HomeworkMapperService homeworkMapperService;

  /**
   * метод загрузки выполненного дз из дто
   * @param dto дто
   */
  public void uploadCompletedHomework(HomeworkCompletionResultDto dto) {
    HomeworkCompletionResult homeworkCompletionResult = homeworkMapperService.mapToHomeworkCompletionResult(
        dto);
    HomeworkCompletionResultEntity homeworkCompletionResultEntity = homeworkMapperService.mapToHomeworkCompletionResultEntity(
        homeworkCompletionResult);
    repository.save(homeworkCompletionResultEntity);
  }

  /**
   * ловец доменного события создания выполненного дз
   * @param event доменное событие
   */
  @EventListener
  public void homeworkCompletionCreatedEvent(HomeworkCompletionCreatedEvent event) {
    HomeworkCompletionResult result = (HomeworkCompletionResult) event.getContent();
    log.info("HomeworkCompletionCreatedEvent...");
    log.info(
        "Домашка студента " + result.getStudentId() + " заданная учителем " + result.getTeacherId()
            + " к уроку " + result.getLessonId() + " выполнена, результат: " + result.getTaskCompletionResult());
  }
}
