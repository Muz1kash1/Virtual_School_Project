package com.fabit.schoolapplication.infrastructure.usecase.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
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
public class ChangeDiscipline {

  private final HomeworkForClassRepository homeworkForClassRepository;
  private final HomeworkForClassMapper homeworkForClassMapper;

  /**
   * метод замены дисциплины в уроке
   *
   * @param lessonId   айди урока
   * @param discipline дисциплина на замену
   */
  @Transactional
  public void execute(LessonId lessonId, Discipline discipline) {
    HomeworkForClass homeworkForClass = homeworkForClassMapper.mapEntityToLesson(
        homeworkForClassRepository.getReferenceById(lessonId.getValue()));
    homeworkForClass.changeDiscipline(discipline);
    HomeworkForClassEntity homeworkForClassEntity = homeworkForClassMapper.mapLessonToEntity(homeworkForClass);
    homeworkForClassEntity.setId(lessonId.getValue());
    homeworkForClassRepository.save(homeworkForClassEntity);
  }
}
