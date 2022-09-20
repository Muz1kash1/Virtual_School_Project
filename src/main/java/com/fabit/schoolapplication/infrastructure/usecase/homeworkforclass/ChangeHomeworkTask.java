package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass.mapper.HomeworkForClassMapper;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeHomeworkTask {

  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;

  /**
   * Метод меняющий домашнее задание.
   *
   * @param homeworkForClassId айди дз где оно меняется
   * @param task               новое задание
   */
  @Transactional
  public void execute(HomeworkForClassId homeworkForClassId, String task) {
    HomeworkForClass homeworkForClass = homeworkForClassMapper.mapEntityToHomeworkForClass(
        homeworkForClassRepository.getReferenceById(homeworkForClassId.getValue()));
    homeworkForClass.setHomeworkText(task);
    HomeworkForClassEntity homeworkForClassEntity = homeworkForClassMapper.mapHomeworkForClassToEntity(
        homeworkForClass);
    homeworkForClassEntity.setId(homeworkForClassId.getValue());
    homeworkForClassRepository.save(homeworkForClassEntity);
  }

}
