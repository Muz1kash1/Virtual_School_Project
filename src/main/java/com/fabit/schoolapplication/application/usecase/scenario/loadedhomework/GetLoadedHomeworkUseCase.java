package com.fabit.schoolapplication.application.usecase.scenario.loadedhomework;

import com.fabit.schoolapplication.application.usecase.access.loadedhomework.LoadedHomeworkService;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetLoadedHomeworkUseCase {

  private final LoadedHomeworkService loadedHomeworkService;

  /**
   * Метод возвращающий домашнюю работу с данным id.
   *
   * @param id идентификатор домашней работы
   * @return доменный обоъект домашней работы
   */
  @Transactional
  public LoadedHomework execute(LoadedHomeworkId id) {
    return loadedHomeworkService.getReferenceById(id);
  }

}
