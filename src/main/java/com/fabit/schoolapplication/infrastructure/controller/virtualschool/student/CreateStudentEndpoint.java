package com.fabit.schoolapplication.infrastructure.controller.virtualschool.student;

import com.fabit.schoolapplication.application.usecase.virtualschool.student.CreateStudent;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
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

  /**
   * Добавить студента.
   *
   * @param student студент
   * @return response entity
   */
  @PostMapping
  public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentDto student) {

    log.info("trying to create: " + student.toString());

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createStudent.execute(student));
  }
}