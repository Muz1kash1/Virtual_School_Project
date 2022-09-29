package com.fabit.schoolapplication.infrastructure.ui.controller.student;

import com.fabit.schoolapplication.application.usecase.scenario.student.CreateStudent;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.mapper.StudentMapperServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student")
public class CreateStudentEndpoint {

  final CreateStudent createStudent;
  final StudentMapperServiceImpl mapperService;

  /**
   * Добавить студента.
   *
   * @param studentDto студент
   * @return response entity
   */
  @PostMapping
  public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentDto studentDto) {
    log.info("trying to create: " + studentDto.toString());
    Student student = mapperService.mapToStudent(studentDto);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(mapperService.mapToStudentEntity(createStudent.execute(student)));
  }
}