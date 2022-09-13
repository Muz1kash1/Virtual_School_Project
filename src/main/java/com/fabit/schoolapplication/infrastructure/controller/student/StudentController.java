package com.fabit.schoolapplication.infrastructure.controller.student;

import com.fabit.schoolapplication.infrastructure.usecase.student.CreateStudent;
import com.fabit.schoolapplication.infrastructure.usecase.student.EditStudent;
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
  final EditStudent editStudent;

  @PostMapping("/addStudent")
  public ResponseEntity<String> addStudent(@RequestBody StudentDto student) {
    log.info("trying to create: " + student.toString());
    createStudent.createStudent(student);
    return ResponseEntity.ok().body("Студент создан");
  }

  @PostMapping("/changeBirthCertificate")
  public ResponseEntity<String> changeBirthCertificateStudent(@RequestBody StudentDto student) {
    log.info("trying to change BirthCertificate: " + student.getBirthCertificate());
    editStudent.changeBirthCertificate(student);
    return ResponseEntity.ok().body("Свидетельство о рождении изменено");
  }
  @PostMapping("/changeSnils")
  public ResponseEntity<String> changeSnilsStudent(@RequestBody StudentDto student) {
    log.info("trying to change BirthCertificate: " + student.getBirthCertificate());
    editStudent.changeBirthCertificate(student);
    return ResponseEntity.ok().body("СНИЛС изменен");
  }

  @PostMapping("/addPassport")
  public ResponseEntity<String> addPassportStudent(@RequestBody StudentDto student) {
    log.info("trying to add Passport: " + student.getPassport());
    editStudent.addPassport(student);
    return ResponseEntity.ok().body("Паспорт добвлен");
  }
}