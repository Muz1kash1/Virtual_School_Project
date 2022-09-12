package com.fabit.schoolapplication.domain.educatioprogress;

import com.fabit.schoolapplication.domain.lesson.LessonId;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
/**
 * Агрегат отметки об успеваемости
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
public class EducationProgress {
  private EducationProgressId educationProgressId;
  private StudentId studentId;
  private TeacherId teacherId;
  private LessonId lessonId;
  private LocalDateTime whenCreated;

  private EducationProgress() {}

  /**
   * Статическая фабрика объекта успеваемости
   *
   * @param educationProgressId id отметки
   * @param studentId id ученика
   * @param teacherId id учителя
   * @param lessonId id урока за который выставлена отметка
   * @return объект отметки об успеваемости
   */
  public static EducationProgress of(
      EducationProgressId educationProgressId,
      StudentId studentId,
      TeacherId teacherId,
      LessonId lessonId) {
    EducationProgress educationProgress = new EducationProgress();
    educationProgress.educationProgressId = educationProgressId;
    educationProgress.studentId = studentId;
    educationProgress.teacherId = teacherId;
    educationProgress.lessonId = lessonId;
    educationProgress.whenCreated = LocalDateTime.now();
    return educationProgress;
  }
}
