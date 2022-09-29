package com.fabit.schoolapplication.application.usecase.scenario.loadedhomework;

import com.fabit.schoolapplication.application.usecase.access.loadedhomework.LoadedHomeworkService;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompleteHomeworkUseCase {

  final LoadedHomeworkService loadedHomeworkService;

  /**
   * Метод загрузки выполненного ДЗ из Доменного объекта.
   *
   * @param loadedHomework доменный объект
   */
  public void uploadCompletedHomework(LoadedHomework loadedHomework) {
    loadedHomeworkService.save(loadedHomework);
  }

}
