package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.StudentId;
import com.fabit.schoolapplication.domain.TeacherId;
import com.fabit.schoolapplication.domain.educatioprogress.Mark;
import lombok.Getter;

@Getter
public class HomeworkCompletionResult {

  private final TeacherId teacherId;

  private final StudentId studentId;
  private String taskCompletionResult;
  private Mark markForHomework;


  /**
   * загрузить выполнение домашки
   * @param taskCompletionResult выполнение домашки
   */
  public void uploadTaskCompletionResult(String taskCompletionResult) {
    this.taskCompletionResult = taskCompletionResult;
  }

  /**
   * выставление оценки за домашку
   * @param markForHomework оценка за домашку
   */
  public void checkHomework(Mark markForHomework) {
    this.markForHomework = markForHomework;
  }

  private HomeworkCompletionResult(StudentId studentId, TeacherId teacherId) {
    this.teacherId = teacherId;
    this.studentId = studentId;
  }

  public static HomeworkCompletionResult of(TeacherId teacherId, StudentId studentId) {
    HomeworkCompletionResult homeworkCompletionResult = new HomeworkCompletionResult(studentId,teacherId);
    return homeworkCompletionResult;
  }

}
