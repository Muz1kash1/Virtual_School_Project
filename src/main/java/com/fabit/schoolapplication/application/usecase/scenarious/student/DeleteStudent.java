package com.fabit.schoolapplication.application.usecase.scenarious.student;

import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import lombok.RequiredArgsConstructor;

/**
 * Юзкейс удаления ученика.
 */
@RequiredArgsConstructor
public class DeleteStudent {

  private final StudentService studentService;

  /**
   * Удалить ученика с идентификатором id.
   *
   * @param id - идентификатор ученика
   */
  public void execute(long id) {
    studentService.deleteStudent(id);
  }
}