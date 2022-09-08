package com.fabit.schoolapplication.domain.educatioprogress;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EducationProgress {
  private Long id;
  private Long studentId;
  private Long teacherId;
  private Long lessonId;
  private LocalDateTime whenCreated;
  private Mark mark;

  private EducationProgress() {}

  /**
   * @param id id отметки
   * @param studentId id ученика
   * @param teacherId id учителя
   * @param lessonId id урока за который выставлена отметка
   * @param mark значение отметки
   * @return объект отметки об успеваемости
   */
  public static EducationProgress create(
      Long id, Long studentId, Long teacherId, Long lessonId, Mark mark) {
    EducationProgress educationProgress = new EducationProgress();
    educationProgress.id = id;
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
