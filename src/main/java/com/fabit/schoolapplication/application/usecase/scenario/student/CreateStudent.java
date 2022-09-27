package com.fabit.schoolapplication.application.usecase.scenario.student;

import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
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
    BirthCertificate birthCertificate = student.getBirthCertificate();
    if (!studentService.findBySnils(student.getSnils().getNumberView())) {
      studentService.save(student, birthCertificate);
    } else {
      throw new IllegalArgumentException("студент уже существует");
    }

    return student;
  }
}