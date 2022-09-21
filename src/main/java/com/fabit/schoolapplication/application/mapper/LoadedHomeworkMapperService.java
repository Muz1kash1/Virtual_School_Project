package com.fabit.schoolapplication.application.mapper;

import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomework;
import com.fabit.schoolapplication.domain.loadedhomework.LoadedHomeworkId;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.controller.virtual_school.loadedhomework.dto.LoadedHomeworkDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.loadedhomework.LoadedHomeworkEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadedHomeworkMapperService {

  final LoadedHomeworkRepository loadedHomeworkRepository;

  /**
   * Метод мапинга дто в доменный объект дз.
   *
   * @param dto дто
   * @return доменный объект
   */
  public LoadedHomework mapDtoToHomework(LoadedHomeworkDto dto) {
    LoadedHomework loadedHomework = LoadedHomework.of(
        LoadedHomeworkId.of(loadedHomeworkRepository.getNextId()),
        StudentId.of(dto.getStudentId()),
        HomeworkForClassId.of(dto.getHomeworkForClassId()));
    if (dto.getTaskCompletionResult() != null && !dto.getTaskCompletionResult().equals("")) {
      loadedHomework.uploadTaskCompletionResult(dto.getTaskCompletionResult());
    }
    return loadedHomework;
  }

  /**
   * Метод мапинга доменного объекта выполненного дз в сущность базы данных.
   *
   * @param loadedHomework доменный объект
   * @return сущность базы данных
   */
  public LoadedHomeworkEntity mapHomeworkToHomeworkCompletionResultEntity(
      LoadedHomework loadedHomework) {
    LoadedHomeworkEntity loadedHomeworkEntity = new LoadedHomeworkEntity();
    loadedHomeworkEntity.setId(loadedHomework.getLoadedHomeworkId().getValue());

    loadedHomeworkEntity.setTaskCompletionResult(
        loadedHomework.getTaskCompletionResult());

    loadedHomeworkEntity.setHomeworkForClassId(
        loadedHomework.getHomeworkForClassId().getValue());

    loadedHomeworkEntity.setStudentId(
        loadedHomework.getStudentId().getValue());
    return loadedHomeworkEntity;
  }

  /**
   * Метод мапинга сущности дз в объект доменной модели.
   *
   * @param loadedHomeworkEntity сущность базы данных
   * @return объект доменной модели
   */
  public LoadedHomework mapHomeworkEntityToHomework(LoadedHomeworkEntity loadedHomeworkEntity) {
    LoadedHomework loadedHomework = LoadedHomework.of(LoadedHomeworkId.of(loadedHomeworkEntity.getId()),
        StudentId.of(loadedHomeworkEntity.getStudentId()),
        HomeworkForClassId.of(loadedHomeworkEntity.getHomeworkForClassId()));
    loadedHomework.uploadTaskCompletionResult(loadedHomeworkEntity.getTaskCompletionResult());
    return loadedHomework;
  }

  /**
   * Метод мапинга доменной модели дз в дто.
   *
   * @param loadedHomework доменная модель дз
   * @return дто
   */
  public LoadedHomeworkDto mapHomeworkToHomeworkDto(LoadedHomework loadedHomework) {
    return new LoadedHomeworkDto(
        loadedHomework.getLoadedHomeworkId().getValue(),
        loadedHomework.getStudentId().getValue(),
        loadedHomework.getTaskCompletionResult(),
        loadedHomework.getHomeworkForClassId().getValue());
  }
}
