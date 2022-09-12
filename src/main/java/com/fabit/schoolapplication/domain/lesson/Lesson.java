package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.TeacherId;
import lombok.Getter;

@Getter
public class Lesson {
  private final TeacherId teacherId;

  private final Discipline discipline;

  private String homeworkTask;

  private Lesson(TeacherId teacherId,Discipline discipline) {
    this.teacherId = teacherId;
    this.discipline = discipline;
  }

  public static Lesson of(TeacherId teacherId, Discipline discipline){
    return new Lesson(teacherId,discipline);
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
