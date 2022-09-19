package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.HomeworkForClassEntity;
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
   * метод возвращающий урок по айди
   *
   * @param lessonId айди
   * @return урок по этому айди
   */
  public HomeworkForClassEntity execute(LessonId lessonId) {
    HomeworkForClassEntity homeworkForClassEntity = homeworkForClassRepository.findById(lessonId.getValue()).get();
    HomeworkForClass homeworkForClass = homeworkForClassMapper.mapEntityToLesson(
        homeworkForClassEntity);

    return homeworkForClassMapper.mapLessonToEntity(homeworkForClass);
  }
}
