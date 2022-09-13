package com.fabit.schoolapplication.infrastructure.usecase.homeworkcompletionresult.mapper;

import com.fabit.schoolapplication.domain.homeworkcompletionresult.HomeworkCompletionResult;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.controller.homeworkcompletionresult.dto.HomeworkCompletionResultDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.homeworkcompletionresult.HomeworkCompletionResultEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HomeworkMapperService {

  @Autowired
  LessonRepository lessonRepository;
  @Autowired
  TeacherRepository teacherRepository;
  @Autowired
  StudentRepository studentRepository;

  /**
   * метод мапинга дто в доменный объект выполненного дз
   *
   * @param dto дто
   * @return доменный объект
   */
  public HomeworkCompletionResult mapToHomeworkCompletionResult(HomeworkCompletionResultDto dto) {
    HomeworkCompletionResult homeworkCompletionResult = HomeworkCompletionResult.of(
        TeacherId.of(dto.getTeacherId()),
        StudentId.of(dto.getStudentId()),
        LessonId.of(dto.getLessonId()));
    if (dto.getTaskCompletionResult() != null && !dto.getTaskCompletionResult().equals("")) {
      homeworkCompletionResult.uploadTaskCompletionResult(dto.getTaskCompletionResult());
    }
    return homeworkCompletionResult;
  }

  /**
   * метод мапинга доменного объекта выполненного дз в сущность базы данных
   *
   * @param homeworkCompletionResult доменный объект
   * @return сущность базы данных
   */
  @Transactional
  public HomeworkCompletionResultEntity mapToHomeworkCompletionResultEntity(
      HomeworkCompletionResult homeworkCompletionResult) {
    HomeworkCompletionResultEntity homeworkCompletionResultEntity = new HomeworkCompletionResultEntity();
    homeworkCompletionResultEntity.setTaskCompletionResult(
        homeworkCompletionResult.getTaskCompletionResult());
    homeworkCompletionResultEntity.setLesson(
        lessonRepository.getReferenceById(homeworkCompletionResult.getLessonId().getValue()));
    homeworkCompletionResultEntity.setStudent(
        studentRepository.getReferenceById(homeworkCompletionResult.getStudentId().getValue()));
    homeworkCompletionResultEntity.setTeacherId(
        teacherRepository.getReferenceById(homeworkCompletionResult.getTeacherId().getValue()));

    return homeworkCompletionResultEntity;
  }
}
