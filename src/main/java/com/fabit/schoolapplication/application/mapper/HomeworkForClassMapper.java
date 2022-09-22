package com.fabit.schoolapplication.application.mapper;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.homeworkforclass.dto.HomeworkForClassDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkforclass.HomeworkForClassEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkForClassMapper {
  final HomeworkForClassRepository homeworkForClassRepository;

  /**
   * Метод получения сущности бд из модели домена Дз для класса.
   *
   * @param homeworkForClass дз для класса
   * @return сущность бд
   */
  public HomeworkForClassEntity mapHomeworkForClassToEntity(HomeworkForClass homeworkForClass) {
    HomeworkForClassEntity homeworkForClassEntity = new HomeworkForClassEntity();
    homeworkForClassEntity.setDiscipline(homeworkForClass.getDiscipline());
    homeworkForClassEntity.setHomeworkTask(homeworkForClass.getTask());
    homeworkForClassEntity.setId(homeworkForClassRepository.getNextId());
    homeworkForClassEntity.setDate(homeworkForClass.getDate());
    homeworkForClassEntity.setSchoolClassId(homeworkForClass.getSchoolClassId().getValue());
    return homeworkForClassEntity;
  }

  /**
   * Метод получения доменной модели дз из сущности бд.
   *
   * @param homeworkForClassEntity сущность бд
   * @return доменная модель дз
   */
  public HomeworkForClass mapEntityToHomeworkForClass(
      HomeworkForClassEntity homeworkForClassEntity) {
    HomeworkForClass homeworkForClass = HomeworkForClass.of(homeworkForClassEntity.getDiscipline(),
        homeworkForClassEntity.getDate(),
        SchoolClassId.of(homeworkForClassEntity.getId()),
        HomeworkForClassId.of(homeworkForClassEntity.getId()));
    homeworkForClass.setHomeworkText(homeworkForClassEntity.getHomeworkTask());
    return homeworkForClass;
  }

  /**
   * Метод получения сущности доменной модели дз из дто.
   *
   * @param homeworkForClassDto дто
   * @return сущность доменной модели
   */
  public HomeworkForClass mapDtoToHomeworkForClass(HomeworkForClassDto homeworkForClassDto) {
    return HomeworkForClass.of(
        homeworkForClassDto.getDiscipline(),
        homeworkForClassDto.getDate(),
        SchoolClassId.of(homeworkForClassDto.getSchoolClassId()),
        HomeworkForClassId.of(homeworkForClassDto.getId())
    );
  }

  /**
   * Метод получения дто из доменной модели дз.
   *
   * @param homework доменная модель дз
   * @return дто
   */
  public HomeworkForClassDto mapHomeworkForClassToDto(HomeworkForClass homework) {
    return new HomeworkForClassDto(homework.getId().getValue(),
        homework.getDiscipline(),
        homework.getTask(),
        homework.getSchoolClassId().getValue(),
        homework.getDate());
  }
}
