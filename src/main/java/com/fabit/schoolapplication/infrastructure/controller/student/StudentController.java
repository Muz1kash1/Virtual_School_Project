package com.fabit.schoolapplication.infrastructure.controller.student;

import com.fabit.schoolapplication.infrastructure.controller.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.usecase.student.CreateStudent;
import com.fabit.schoolapplication.infrastructure.usecase.student.DeleteStudent;
import com.fabit.schoolapplication.infrastructure.usecase.student.EditStudent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student")
public class StudentController {
  final CreateStudent createStudent;
  final EditStudent editStudent;
  final DeleteStudent deleteStudent;

  /**
   * Добавить студента
   *
   * @param student студент
   * @return response entity
   */
  @PostMapping("/addStudent")
  public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentDto student) {
    log.info("trying to create: " + student.toString());
    return ResponseEntity.status(HttpStatus.CREATED).body(createStudent.execute(student));
  }

  /**
   * Изменить свидетельство о рождении
   *
   * @param student student
   * @return response entity
   */
  @PutMapping("/changeBirthCertificate")
  public ResponseEntity<StudentEntity> changeBirthCertificateStudent(
      @RequestBody StudentDto student) {
    log.info("trying to change BirthCertificate: " + student.getBirthCertificate());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(editStudent.changeBirthCertificate(student));
  }

  /**
   * Изменить СНИЛС у студента
   *
   * @param student the student
   * @return the response entity
   */
  @PutMapping("/changeSnils")
  public ResponseEntity<StudentEntity> changeSnilsStudent(@RequestBody StudentDto student) {
    log.info("trying to change BirthCertificate: " + student.getBirthCertificate());
    return ResponseEntity.status(HttpStatus.CREATED).body(editStudent.changeSnils(student));
  }

  /**
   * Добавить паспорт студенту
   *
   * @param student student
   * @return response entity
   */
  @PutMapping("/addPassport")
  public ResponseEntity<String> addPassportStudent(@RequestBody StudentDto student) {
    log.info("trying to add Passport: " + student.getPassport());
    editStudent.addPassport(student);
    return ResponseEntity.status(HttpStatus.CREATED).body("Паспорт добвлен");
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable long id) {
    log.info("trying to delete student with id: " + id);
    deleteStudent.deleteStudent(id);
    return ResponseEntity.status(HttpStatus.CREATED).body("Ученик исключен из школы");
  }
}