package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeDiscipline {

  private final LessonRepository lessonRepository;
  private final LessonMapperService lessonMapperService;

  /**
   * метод замены дисциплины в уроке
   *
   * @param lessonId   айди урока
   * @param discipline дисциплина на замену
   */
  @Transactional
  public void execute(LessonId lessonId, Discipline discipline) {
    Lesson lesson = lessonMapperService.mapEntityToLesson(
        lessonRepository.getReferenceById(lessonId.getValue()));
    lesson.changeDiscipline(discipline);
    LessonEntity lessonEntity = lessonMapperService.mapLessonToEntity(lesson);
    lessonEntity.setId(lessonId.getValue());
    lessonRepository.save(lessonEntity);
  }
}
