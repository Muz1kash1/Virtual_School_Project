package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetLesson {
  private final LessonRepository lessonRepository;

  /**
   * метод возвращающий урок по айди
   * @param lessonId айди
   * @return урок по этому айди
   */
  public LessonEntity execute(LessonId lessonId){
    return lessonRepository.getReferenceById(lessonId.getValue());
  }
}
