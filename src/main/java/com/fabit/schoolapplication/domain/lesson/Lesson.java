package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.TeacherId;
import com.fabit.schoolapplication.domain.educatioprogress.Mark;
import java.util.HashMap;
import java.util.Map;

import com.fabit.schoolapplication.domain.StudentId;
import lombok.Getter;

@Getter
public class Lesson {
  private final TeacherId teacherId;

  private String homeworkTask;

  /**
   * отображение множества учеников на гиппермножество оценок
   */
  private final Map<StudentId,Mark> marks;

  private Lesson(TeacherId teacherId) {
    this.marks = new HashMap<>();
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

  /**
   * Метод выставления оценки за урок
   * @param mark оценка за урок
   */
  public void setMarkForLesson(StudentId studentId,Mark mark) {
    marks.put(studentId,mark);
  }
}
