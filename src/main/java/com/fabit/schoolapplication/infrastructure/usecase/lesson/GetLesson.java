package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetLesson {

  private final LessonRepository lessonRepository;
  private final LessonMapperService lessonMapperService;

  /**
   * метод возвращающий урок по айди
   *
   * @param lessonId айди
   * @return урок по этому айди
   */
  public LessonEntity execute(LessonId lessonId) {
    LessonEntity lessonEntity = lessonRepository.findById(lessonId.getValue()).get();
    Lesson lesson = lessonMapperService.mapEntityToLesson(lessonEntity);

    return lessonMapperService.mapLessonToEntity(lesson);
  }
}
