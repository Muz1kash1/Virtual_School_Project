package com.fabit.schoolapplication.infrastructure.ui.controller.loadedhomework;

import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.CompleteHomeworkUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.GetLoadedHomeworkUseCase;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.infrastructure.ui.controller.loadedhomework.dto.LoadedHomeworkDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class LoadedHomeworkController {

  final CompleteHomeworkUseCase completeHomeworkUseCase;
  final GetLoadedHomeworkUseCase getLoadedHomeworkUseCase;
  final LoadedHomeworkMapperService loadedHomeworkMapperService;

  /**
   * Загружает выполненное дз.
   *
   * @param loadedHomeworkDto дто с выполненным дз
   * @return строка подтверждающая что все ок
   */
  @PostMapping(value = "/homework", produces = "application/json")
  public ResponseEntity<String> sendCompletedHomework(
      @RequestBody LoadedHomeworkDto loadedHomeworkDto) {

    log.info("пытаемся загрузить выполненную домашку");
    completeHomeworkUseCase.uploadCompletedHomework(
        loadedHomeworkMapperService.mapDtoToHomework(loadedHomeworkDto)
    );

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Домашняя работа загружена");
  }

  /**
   * Возвращает выполненное дз.
   *
   * @param id id выполненного дз
   * @return выполненное дз
   */

  @GetMapping(value = "/homework/{id}", produces = "application/json")
  public ResponseEntity<LoadedHomeworkDto> getCompletedHomework(@PathVariable long id) {

    return ResponseEntity
        .ok()
        .body(loadedHomeworkMapperService.mapHomeworkToHomeworkDto(
                getLoadedHomeworkUseCase.execute(LoadedHomeworkId.of(id))
            )
        );
  }

}
