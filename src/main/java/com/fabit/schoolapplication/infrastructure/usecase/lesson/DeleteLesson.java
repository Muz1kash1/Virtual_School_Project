package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class DeleteLesson {
  private final LessonRepository lessonRepository;

  /**
   * метод удаления урока
   * @param lessonId айди урока
   */
  public void execute(LessonId lessonId){
    lessonRepository.deleteById(lessonId.getValue());
  }
}
