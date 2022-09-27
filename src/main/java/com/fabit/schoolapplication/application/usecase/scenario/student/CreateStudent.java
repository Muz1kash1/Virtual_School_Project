package com.fabit.schoolapplication.application.usecase.scenario.student;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.application.usecase.scenario.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.student.Student;
import lombok.RequiredArgsConstructor;

/**
 * Юзкейс создания ученика.
 */
@RequiredArgsConstructor
public class CreateStudent {

  private final StudentService studentService;
  private final StudentMapperService studentMapperService;

  /**
   * Создать ученика из StudentDTO.
   *
   * @param studentDto - studentDto
   * @return студент
   */
  public Student execute(StudentDto studentDto) {

    Student student = studentMapperService.mapToStudent(studentDto);

    if (!studentService.findBySnils(studentDto.getSnils().toString())) {
      studentService.save(student);
    } else {
      throw new IllegalArgumentException("студент уже существует");
    }

    return student;
  }
}