package com.fabit.schoolapplication.domain.educatioprogress;

import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Агрегат отметки об успеваемости.
 *
 * @author SmirnovMA
 */
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class EducationProgress {
  private EducationProgressId educationProgressId;
  private StudentId studentId;
  private TeacherId teacherId;
  private LessonId lessonId;
  private LocalDateTime whenCreated;

  private List<Mark> marks = new ArrayList<>(2);

  /**
   * Статическая фабрика объекта успеваемости.
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
      LessonId lessonId,
      List<Mark> marks) {
    EducationProgress educationProgress = new EducationProgress();
    educationProgress.educationProgressId = educationProgressId;
    educationProgress.studentId = studentId;
    educationProgress.teacherId = teacherId;
    educationProgress.lessonId = lessonId;
    educationProgress.marks = marks;
    educationProgress.whenCreated = LocalDateTime.now();
    return educationProgress;
  }
}
