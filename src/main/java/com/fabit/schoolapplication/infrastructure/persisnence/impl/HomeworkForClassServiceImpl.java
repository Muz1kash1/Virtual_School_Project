package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.usecase.access.homeworkforclass.HomeworkForClassService;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HomeworkForClassServiceImpl implements HomeworkForClassService {

  final HomeworkForClassMapper homeworkForClassMapper;
  final HomeworkForClassRepository homeworkForClassRepository;

  /**
   * задать классу дз доменным объектом.
   *
   * @param homeworkForClass доменный объект
   */
  @Override
  public void save(HomeworkForClass homeworkForClass) {
    homeworkForClassRepository.save(
        homeworkForClassMapper.mapHomeworkForClassToEntity(homeworkForClass)
    );
  }

  /**
   * Удалить дз для класса по id.
   *
   * @param id id дз для класса
   */
  @Override
  public void deleteById(HomeworkForClassId id) {
    homeworkForClassRepository.deleteById(id.getValue());
  }

  /**
   * Найти дз для класса по id.
   *
   * @param id id дз для класса
   * @return дз для класса
   */
  @Override
  public HomeworkForClass findById(HomeworkForClassId id) {
    return homeworkForClassMapper.mapEntityToHomeworkForClass(
        homeworkForClassRepository.getReferenceById(id.getValue())
    );
  }

  /**
   * Удалить все дз для классов
   */
  @Override
  public void deleteAll() {
    homeworkForClassRepository.deleteAll();
  }

  /**
   * Изменить дз для класса.
   *
   * @param id   id класса
   * @param task новое задание
   */
  @Override
  public void changeHomework(HomeworkForClassId id, String task) {
    homeworkForClassRepository.getReferenceById(id.getValue()).setHomeworkTask(task);
  }
}
