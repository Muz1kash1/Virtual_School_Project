package com.fabit.schoolapplication.domain.schoolclass.event;

import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.ToString;

/**
 * EVENT: удаление ученика из школьного класса.
 */
@ToString
public class SchoolClassRemovedStudentEvent implements SchoolClassEvent{

  private final StudentId studentId;

  public SchoolClassRemovedStudentEvent(StudentId studentId) {
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
