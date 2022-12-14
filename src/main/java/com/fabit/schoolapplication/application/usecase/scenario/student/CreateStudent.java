package com.fabit.schoolapplication.application.usecase.scenario.student;

import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.domain.student.Student;
import lombok.RequiredArgsConstructor;

/**
 * Юзкейс создания ученика.
 */
@RequiredArgsConstructor
public class CreateStudent {

  private final StudentService studentService;

  /**
   * Создать ученика.
   *
   * @param student - студент
   * @return студент
   */
  public Student execute(Student student) {
    if (!studentService.findBySnils(student.getSnils().getNumberView())) {
      studentService.save(student);
    } else {
      throw new IllegalArgumentException("студент уже существует");
    }

    return student;
  }

}