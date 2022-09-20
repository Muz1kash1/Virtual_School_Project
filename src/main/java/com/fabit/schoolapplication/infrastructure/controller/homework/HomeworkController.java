package com.fabit.schoolapplication.infrastructure.controller.homework;

import com.fabit.schoolapplication.infrastructure.controller.homework.dto.HomeworkDto;
import com.fabit.schoolapplication.infrastructure.usecase.homework.CompleteHomework;
import com.fabit.schoolapplication.infrastructure.usecase.homework.GetHomework;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class HomeworkController {

  final CompleteHomework completeHomework;
  final GetHomework getHomework;


  /**
   * загружает выполненное дз
   *
   * @param homeworkDto дто с выполненным тз
   * @return строка подтверждающая что все ок
   */
  @PostMapping(value = "/homework", produces = "application/json")
  public ResponseEntity<String> sendCompletedHomework(
      @RequestBody HomeworkDto homeworkDto) {
    log.info("пытаемся загрузить выполненную домашку");
    completeHomework.uploadCompletedHomework(homeworkDto);
    return ResponseEntity.status(HttpStatus.CREATED).body("Домашняя работа загружена");
  }

  /**
   * возвращает выполненное дз
   * @param id id выполненного дз
   * @return выполненное дз
   */

  @GetMapping(value = "/homework/{id}", produces = "application/json")
  public ResponseEntity<HomeworkDto> getCompletedHomework(
      @PathVariable long id) {
    return ResponseEntity.ok().body(getHomework.execute(id));
  }

}
