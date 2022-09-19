package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.HomeworkForClassEntity;
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
   * метод меняющий домашнее задание
   * @param lessonId айди урока где оно меняется
   * @param task новое дзд
   */
  @Transactional
  public void execute(LessonId lessonId, String task) {
    HomeworkForClass homeworkForClass = homeworkForClassMapper.mapEntityToLesson(
        homeworkForClassRepository.getReferenceById(lessonId.getValue()));
    homeworkForClass.setHomeworkText(task);
    HomeworkForClassEntity homeworkForClassEntity = homeworkForClassMapper.mapLessonToEntity(homeworkForClass);
    homeworkForClassEntity.setId(lessonId.getValue());
    homeworkForClassRepository.save(homeworkForClassEntity);
  }

}
