package com.fabit.schoolapplication.infrastructure.usecase.lesson.mapper;

import com.fabit.schoolapplication.domain.lesson.Lesson;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.lesson.LessonEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonMapperService {

  TeacherRepository teacherRepository;


  public LessonEntity mapLessonToEntity(Lesson lesson) {
    LessonEntity lessonEntity = new LessonEntity();
    lessonEntity.setTeacher(teacherRepository.getReferenceById(lesson.getTeacherId().getValue()));
    lessonEntity.setDiscipline(lesson.getDiscipline());
    lessonEntity.setHomeworkTask(lesson.getHomeworkTask());
    lessonEntity.setId(1l);
    return lessonEntity;
  }

  public Lesson mapEntityToLesson(LessonEntity lessonEntity){
    Lesson lesson = Lesson.of(TeacherId.of(lessonEntity.getTeacher().getId()),lessonEntity.getDiscipline());
    lesson.setHomeworkText(lessonEntity.getHomeworkTask());
    return lesson;
  }
}
