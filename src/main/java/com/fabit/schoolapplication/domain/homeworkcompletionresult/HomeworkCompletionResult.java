package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.LessonId;
import com.fabit.schoolapplication.domain.StudentId;
import com.fabit.schoolapplication.domain.TeacherId;
import lombok.Getter;

@Getter
public class HomeworkCompletionResult {

  private final TeacherId teacherId;
  private final StudentId studentId;
  private String taskCompletionResult;

  private final LessonId lessonId;


  /**
   * загрузить выполнение домашки
   *
   * @param taskCompletionResult выполнение домашки
   */
  public void uploadTaskCompletionResult(String taskCompletionResult) {
    this.taskCompletionResult = taskCompletionResult;
  }


  private HomeworkCompletionResult(StudentId studentId, TeacherId teacherId, LessonId lessonId) {
    this.teacherId = teacherId;
    this.studentId = studentId;
    this.lessonId = lessonId;
  }

  public static HomeworkCompletionResult of(TeacherId teacherId, StudentId studentId,
      LessonId lessonId) {
    return new HomeworkCompletionResult(studentId,
        teacherId, lessonId);
  }

}
