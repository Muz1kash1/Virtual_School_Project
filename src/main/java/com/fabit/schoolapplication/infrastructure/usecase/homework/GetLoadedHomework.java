package com.fabit.schoolapplication.infrastructure.usecase.homework;

import com.fabit.schoolapplication.infrastructure.controller.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homework.mapper.LoadedHomeworkMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetLoadedHomework {

  @Autowired
  LoadedHomeworkMapperService loadedHomeworkMapperService;
  @Autowired
  LoadedHomeworkRepository loadedHomeworkRepository;

  /**
   * Метод возвращающий домашнюю работу с данным id.
   *
   * @param id id домашней работы
   * @return дто домашнней работы
   */
  public LoadedHomeworkDto execute(long id) {
    return loadedHomeworkMapperService.mapHomeworkToHomeworkDto(
        loadedHomeworkMapperService.mapHomeworkEntityToHomework(loadedHomeworkRepository.getReferenceById(id)));
  }
}
