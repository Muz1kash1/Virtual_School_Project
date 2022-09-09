package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EducationProgress {
  private long educationProgressId;
  private long studentId;
  private long teacherId;
  private long lessonId;
  private LocalDateTime whenCreated;
  private Mark mark;

  private EducationProgress() {}

  /**
   * Статическая фабрика объекта успеваемости
   *
   * @param educationProgressId id отметки
   * @param studentId id ученика
   * @param teacherId id учителя
   * @param lessonId id урока за который выставлена отметка
   * @param mark значение отметки
   * @return объект отметки об успеваемости
   */
  public static EducationProgress create(
      long educationProgressId, long studentId, long teacherId, long lessonId, Mark mark) {
    EducationProgress educationProgress = new EducationProgress();
    educationProgress.educationProgressId = educationProgressId;
    educationProgress.studentId = studentId;
    educationProgress.teacherId = teacherId;
    educationProgress.lessonId = lessonId;
    educationProgress.mark = mark;
    educationProgress.whenCreated = LocalDateTime.now();
    return educationProgress;
  }

  /**
   * Изменение отметки
   *
   * @param mark новая отметка об успеваемости
   */
  public void changeMark(Mark mark) {
    this.mark = mark;
  }
}
