package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.usecase.access.loadedhomework.LoadedHomeworkService;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoadedHomeworkServiceImpl implements LoadedHomeworkService {
  final LoadedHomeworkRepository loadedHomeworkRepository;
  final LoadedHomeworkMapperService loadedHomeworkMapperService;

  @Override
  public void save(LoadedHomework loadedHomework) {
    loadedHomeworkRepository.save(
      loadedHomeworkMapperService.mapHomeworkToHomeworkCompletionResultEntity(loadedHomework));
  }

  /**
   * Получить домашнюю работу по id.
   *
   * @param id идентификатор
   * @return домашняя работа
   */
  @Override
  public LoadedHomework getReferenceById(LoadedHomeworkId id) {
    return loadedHomeworkMapperService.mapHomeworkEntityToHomework(
      loadedHomeworkRepository.getReferenceById(id.getValue()));
  }

  /**
   * Удалить все домашние работы.
   */
  @Override
  public void deleteAll() {
    loadedHomeworkRepository.deleteAll();
  }
}
