package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.homeworkforclass.dto.HomeworkForClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetHomeworkForClass {

  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;

  /**
   * Метод возвращающий дз класса по идентификатору.
   *
   * @param homeworkForClassId айди
   * @return дз по этому айди
   */
  public HomeworkForClassDto execute(long homeworkForClassId) {

    HomeworkForClassEntity homeworkForClassEntity
        = homeworkForClassRepository.findById(homeworkForClassId).get();

    HomeworkForClass homeworkForClass
        = homeworkForClassMapper.mapEntityToHomeworkForClass(homeworkForClassEntity);

    return homeworkForClassMapper.mapHomeworkForClassToDto(homeworkForClass);
  }
}
