package com.fabit.schoolapplication.infrastructure.usecase.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LessonRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper.LessonMapperService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeTeacher {
  private final LessonMapperService lessonMapperService;
  private final LessonRepository lessonRepository;
  private final TeacherRepository teacherRepository;

  /**
   * метод меняющий текущего учителя у урока
   * @param lessonId айди урока
   * @param teacherId айди учителя
   */
  @Transactional
  public void execute(LessonId lessonId, TeacherId teacherId) {
    Lesson lesson = lessonMapperService.mapEntityToLesson(lessonRepository.getReferenceById(lessonId.getValue()));
    lesson.changeTeacher(teacherId);
    LessonEntity lessonEntity = lessonMapperService.mapLessonToEntity(lesson);
    lessonEntity.setId(lessonId.getValue());
    lessonRepository.save(lessonEntity);
  }
}
