package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeHomeworkTask {
  private final LessonRepository lessonRepository;
  private final LessonMapperService lessonMapperService;

  public void execute(LessonId lessonId,String task){
    Lesson lesson = lessonMapperService.mapEntityToLesson(lessonRepository.getReferenceById(lessonId.getValue()));
    lesson.setHomeworkText(task);
    LessonEntity lessonEntity = lessonMapperService.mapLessonToEntity(lesson);
    lessonEntity.setId(lessonId.getValue());
    lessonRepository.save(lessonEntity);
  }

}
