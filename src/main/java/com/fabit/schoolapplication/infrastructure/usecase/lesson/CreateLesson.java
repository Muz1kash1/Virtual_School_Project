package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateLesson {
  private final LessonRepository lessonRepository;
  private final LessonMapperService lessonMapperService;

  /**
   * этот метод создает урок
   * @param teacherId айди учителя
   * @param discipline дисциплина урока
   */
  @Transactional
  public void execute(TeacherId teacherId, Discipline discipline){
    Lesson lesson = Lesson.of(teacherId,discipline);
    lessonRepository.save(lessonMapperService.mapLessonToEntity(lesson));
  }
}
