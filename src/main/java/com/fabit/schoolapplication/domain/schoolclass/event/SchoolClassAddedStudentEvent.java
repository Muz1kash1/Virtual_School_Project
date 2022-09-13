package com.fabit.schoolapplication.domain.schoolclass.event;

import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.ToString;

/**
 * EVENT: добавление ученика в школьный класс.
 */
@ToString
public class SchoolClassAddedStudentEvent implements SchoolClassEvent {

  private final StudentId studentId;

  public SchoolClassAddedStudentEvent(StudentId studentId) {
    this.studentId = studentId;
  }

  /**
   * Получить контент события.
   *
   * @return studentId
   */
  @Override
  public Object getContent() {
    return studentId;
  }
}
