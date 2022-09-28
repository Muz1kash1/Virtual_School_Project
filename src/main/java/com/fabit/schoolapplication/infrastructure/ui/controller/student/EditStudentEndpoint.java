package com.fabit.schoolapplication.infrastructure.ui.controller.student;

import com.fabit.schoolapplication.application.usecase.scenario.student.EditStudent;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.Passport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.mapper.StudentMapperServiceImpl;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student")
public class EditStudentEndpoint {

  final EditStudent editStudent;
  final StudentMapperServiceImpl mapperService;

  /**
   * Изменить свидетельство о рождении.
   *
   * @param studentDto student
   * @return response entity
   */
  @PutMapping("/change-birthcertificate")
  public ResponseEntity<BirthCertificate> changeBirthCertificateStudent(
      @RequestBody StudentDto studentDto) {

    log.info("trying to change BirthCertificate: " + studentDto.getBirthCertificate());

    Student student = mapperService.mapToStudent(studentDto);
    return ResponseEntity
        .ok()
        .body(editStudent.changeBirthCertificate(student));
  }

  /**
   * Изменить СНИЛС у студента.
   *
   * @param studentDto the student
   * @return the response entity
   */
  @PutMapping("/change-snils")
  public ResponseEntity<Snils> changeSnilsStudent(@RequestBody StudentDto studentDto) {

    log.info("trying to change BirthCertificate: " + studentDto.getBirthCertificate());

    Student student = mapperService.mapToStudent(studentDto);
    return ResponseEntity
        .ok()
        .body(editStudent.changeSnils(student));
  }

  /**
   * Добавить паспорт студенту.
   *
   * @param studentDto student
   * @return response entity
   */
  @PutMapping("/add-passport")
  public ResponseEntity<Passport> addPassportStudent(@RequestBody StudentDto studentDto) {

    log.info("trying to add RussianPassport: " + studentDto.getPassport());
    Student student = mapperService.mapToStudent(studentDto);

    return ResponseEntity
        .ok()
        .body(editStudent.addPassport(student));
  }
}
