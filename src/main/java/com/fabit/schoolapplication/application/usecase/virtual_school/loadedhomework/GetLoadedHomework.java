package com.fabit.schoolapplication.application.usecase.virtual_school.loadedhomework;

import com.fabit.schoolapplication.infrastructure.controller.virtual_school.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import com.fabit.schoolapplication.application.mapper.LoadedHomeworkMapperService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetLoadedHomework {


  private final LoadedHomeworkMapperService loadedHomeworkMapperService;
  private final LoadedHomeworkRepository loadedHomeworkRepository;

  /**
   * Метод возвращающий домашнюю работу с данным id.
   *
   * @param id id домашней работы
   * @return дто домашнней работы
   */
  @Transactional
  public LoadedHomeworkDto execute(long id) {
    return loadedHomeworkMapperService.mapHomeworkToHomeworkDto(
        loadedHomeworkMapperService.mapHomeworkEntityToHomework(loadedHomeworkRepository.getReferenceById(id)));
  }
}
