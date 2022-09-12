package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.StudentId;
import com.fabit.schoolapplication.domain.TeacherId;
import lombok.Getter;

@Getter
public class HomeworkCompletionResult {

  private final TeacherId teacherId;
  private final StudentId studentId;
  private String taskCompletionResult;


  /**
   * загрузить выполнение домашки
   *
   * @param taskCompletionResult выполнение домашки
   */
  public void uploadTaskCompletionResult(String taskCompletionResult) {
    this.taskCompletionResult = taskCompletionResult;
  }


  private HomeworkCompletionResult(StudentId studentId, TeacherId teacherId) {
    this.teacherId = teacherId;
    this.studentId = studentId;
  }

  public static HomeworkCompletionResult of(TeacherId teacherId, StudentId studentId) {
    return new HomeworkCompletionResult(studentId,
        teacherId);
  }

}
