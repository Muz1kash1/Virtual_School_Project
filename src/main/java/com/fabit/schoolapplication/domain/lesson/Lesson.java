package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.TeacherId;
import lombok.Getter;

@Getter
public class Lesson {
  private final TeacherId teacherId;

  private String homeworkTask;

  private Lesson(TeacherId teacherId) {
    this.teacherId = teacherId;
  }

  public static Lesson of(TeacherId teacherId){
    return new Lesson(teacherId);
  }

  /**
   * Метод задания текста домашнего задания для всех прикрепленных к этому уровку студентов
   *
   * @param homeworkTask - текст домашнего задания
   */
  public void setHomeworkText(String homeworkTask) {
    this.homeworkTask = homeworkTask;
  }

}
