package com.fabit.schoolapplication.infrastructure.usecase.homework.mapper;

import com.fabit.schoolapplication.domain.homework.Homework;
import com.fabit.schoolapplication.domain.homework.HomeworkId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.controller.homework.dto.HomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homework.HomeworkEntity;
import org.springframework.stereotype.Service;

@Service
public class HomeworkMapperService {

  /**
   * метод мапинга дто в доменный объект дз
   *
   * @param dto дто
   * @return доменный объект
   */
  public Homework mapDtoToHomework(HomeworkDto dto) {
    Homework homework = Homework.of(
        HomeworkId.of(dto.getHomeworkId()),
        StudentId.of(dto.getStudentId()),
        HomeworkForClassId.of(dto.getHomeworkForClassId()));
    if (dto.getTaskCompletionResult() != null && !dto.getTaskCompletionResult().equals("")) {
      homework.uploadTaskCompletionResult(dto.getTaskCompletionResult());
    }
    return homework;
  }

  /**
   * метод мапинга доменного объекта выполненного дз в сущность базы данных
   *
   * @param homework доменный объект
   * @return сущность базы данных
   */
  public HomeworkEntity mapHomeworkToHomeworkCompletionResultEntity(
      Homework homework) {
    HomeworkEntity homeworkEntity = new HomeworkEntity();
    homeworkEntity.setId(homework.getHomeworkId().getValue());

    homeworkEntity.setTaskCompletionResult(
        homework.getTaskCompletionResult());

    homeworkEntity.setHomeworkForClassId(
        homework.getHomeworkForClassId().getValue());

    homeworkEntity.setStudent(
        homework.getStudentId().getValue());
    return homeworkEntity;
  }

  /**
   * метод мапинга сущности дз в объект доменной модели
   * @param homeworkEntity сущность базы данных
   * @return объект доменной модели
   */
  public Homework mapHomeworkEntityToHomework(HomeworkEntity homeworkEntity) {
    Homework homework = Homework.of(HomeworkId.of(homeworkEntity.getId()),
        StudentId.of(homeworkEntity.getStudent()),
        HomeworkForClassId.of(homeworkEntity.getHomeworkForClassId()));
    homework.uploadTaskCompletionResult(homeworkEntity.getTaskCompletionResult());
    return homework;
  }

  /**
   * метод мапинга доменной модели дз в дто
   * @param homework доменная модель дз
   * @return дто
   */
  public HomeworkDto mapHomeworkToHomeworkDto(Homework homework) {
    return new HomeworkDto(homework.getHomeworkId().getValue(), homework.getStudentId().getValue(),
        homework.getTaskCompletionResult(), homework.getHomeworkForClassId().getValue());
  }
}
