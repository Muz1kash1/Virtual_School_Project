package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.infrastructure.controller.homeworkforclass.dto.HomeworkForClassDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetHomeworkForClass {

  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;

  /**
   * метод возвращающий дз класса по айди
   *
   * @param homeworkForClassId айди
   * @return дз по этому айди
   */
  public HomeworkForClassDto execute(long homeworkForClassId) {
    HomeworkForClassEntity homeworkForClassEntity = homeworkForClassRepository.findById(homeworkForClassId).get();
    HomeworkForClass homeworkForClass = homeworkForClassMapper.mapEntityToHomeworkForClass(
        homeworkForClassEntity);

    return homeworkForClassMapper.mapHomeworkForClassToDto(homeworkForClass);
  }
}
