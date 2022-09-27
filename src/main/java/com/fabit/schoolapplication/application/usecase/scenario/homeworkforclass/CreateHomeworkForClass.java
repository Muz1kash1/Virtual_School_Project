package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.homeworkforclass.dto.HomeworkForClassDto;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateHomeworkForClass {

  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;


  /**
   * Метод создающий дз для класса.
   *
   * @param discipline    дисциплина
   * @param date          дата сдачи
   * @param schoolClassId класс
   */
  public void execute(Discipline discipline, LocalDate date, SchoolClassId schoolClassId) {

    HomeworkForClass homeworkForClass = HomeworkForClass.of(
        discipline,
        date,
        schoolClassId,
        HomeworkForClassId.of(1L));

    homeworkForClassRepository.save(
        homeworkForClassMapper.mapHomeworkForClassToEntity(homeworkForClass));
  }

  /**
   * Метод создающий дз для класса из дто.
   *
   * @param homeworkForClassDto дто
   */
  public void execute(HomeworkForClassDto homeworkForClassDto) {

    HomeworkForClass homeworkForClass
        = homeworkForClassMapper.mapDtoToHomeworkForClass(homeworkForClassDto);
    HomeworkForClassEntity homeworkForClassEntity
        = homeworkForClassMapper.mapHomeworkForClassToEntity(homeworkForClass);

    homeworkForClassRepository.save(homeworkForClassEntity);
  }
}
