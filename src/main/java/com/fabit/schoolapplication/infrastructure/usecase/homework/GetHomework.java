package com.fabit.schoolapplication.infrastructure.usecase.homework;

import com.fabit.schoolapplication.infrastructure.controller.homework.dto.HomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homework.mapper.HomeworkMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetHomework {
  @Autowired
  HomeworkMapperService homeworkMapperService;
  @Autowired
  HomeworkRepository homeworkRepository;

  /**
   * метод возвращающий домашнюю работу с данным id
   * @param id id домашней работы
   * @return дто домашнней работы
   */
  public HomeworkDto execute(long id){
    return homeworkMapperService.mapHomeworkToHomeworkDto(homeworkMapperService.mapHomeworkEntityToHomework(homeworkRepository.getReferenceById(id)));
  }
}
