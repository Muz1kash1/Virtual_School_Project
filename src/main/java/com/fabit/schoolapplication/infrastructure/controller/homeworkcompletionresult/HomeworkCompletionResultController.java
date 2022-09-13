package com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult;

import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.CompleteHomework;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HomeworkCompletionResultController {

  final CompleteHomework completeHomework;

  /**
   * загружает выполненное дз(пут для идемпотентности)
   * @param homeworkCompletionResultDto дто с выполненным тз
   * @return строка подтверждающая что все ок
   */
  @PutMapping("/Homework")
  public ResponseEntity<String> sendCompletedHomework(
      @RequestBody HomeworkCompletionResultDto homeworkCompletionResultDto) {
    log.info("пытаемся загрузить выполненную домашку");
    completeHomework.uploadCompletedHomework(homeworkCompletionResultDto);
    return ResponseEntity.created(URI.create(
        "/Homework/" + homeworkCompletionResultDto.getLessonId() + "/"
            + homeworkCompletionResultDto.getStudentId())).body("Домашняя работа загружена");
  }

}
