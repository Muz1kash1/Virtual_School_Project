package com.fabit.schoolapplication.application.usecase.virtualschool.student;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Юзкейс создания ученика.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateStudent {

  private final StudentRepository studentRepository;
  private final StudentMapperService studentMapperService;

  /**
   * Создать ученика из StudentDTO.
   *
   * @param studentDto - studentDto
   */
  public StudentEntity execute(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);

    if (studentRepository.findBySnils(studentDto.getSnils().toString()) == null) {
      studentRepository.save(studentEntity);
    }

    return studentEntity;
  }
}