package com.fabit.schoolapplication.application.usecase.virtualschool.homeworkforclass;

import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeDiscipline {

  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;

  /**
   * Метод замены дисциплины в задании.
   *
   * @param homeworkForClassId айди задания
   * @param discipline         дисциплина на замену
   */
  @Transactional
  public void execute(HomeworkForClassId homeworkForClassId, Discipline discipline) {

    HomeworkForClass homeworkForClass = homeworkForClassMapper.mapEntityToHomeworkForClass(
            homeworkForClassRepository.getReferenceById(homeworkForClassId.getValue()));

    homeworkForClass.changeDiscipline(discipline);

    HomeworkForClassEntity homeworkForClassEntity
        = homeworkForClassMapper.mapHomeworkForClassToEntity(homeworkForClass);

    homeworkForClassEntity.setId(homeworkForClassId.getValue());

    homeworkForClassRepository.save(homeworkForClassEntity);
  }
}
