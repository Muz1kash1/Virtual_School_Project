package com.fabit.schoolapplication.infrastructure.controller;

import com.fabit.schoolapplication.infrastructure.usecase.student.CreateStudent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StudentController {
  final CreateStudent createStudent;

  @PostMapping("/addStudent")
  public ResponseEntity<String> addOrder(@RequestBody StudentDto student) {
    log.info("trying to create: " + student.toString());
    createStudent.createStudent(student);
    return ResponseEntity.ok().body("Студент создан");
  }
}