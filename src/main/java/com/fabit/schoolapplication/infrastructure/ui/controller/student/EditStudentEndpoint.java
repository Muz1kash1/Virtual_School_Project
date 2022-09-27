package com.fabit.schoolapplication.infrastructure.ui.controller.student;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.application.usecase.scenario.student.EditStudent;
import com.fabit.schoolapplication.application.usecase.scenario.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
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
  final StudentMapperService mapperService;

  /**
   * Изменить свидетельство о рождении.
   *
   * @param student student
   * @return response entity
   */
  @PutMapping("/change-birthcertificate")
  public ResponseEntity<BirthCertificate> changeBirthCertificateStudent(
      @RequestBody StudentDto student) {

    log.info("trying to change BirthCertificate: " + student.getBirthCertificate());

    return ResponseEntity
        .ok()
        .body(editStudent.changeBirthCertificate(student));
  }

  /**
   * Изменить СНИЛС у студента.
   *
   * @param student the student
   * @return the response entity
   */
  @PutMapping("/change-snils")
  public ResponseEntity<Snils> changeSnilsStudent(@RequestBody StudentDto student) {

    log.info("trying to change BirthCertificate: " + student.getBirthCertificate());

    return ResponseEntity
        .ok()
        .body(editStudent.changeSnils(student));
  }

  /**
   * Добавить паспорт студенту.
   *
   * @param student student
   * @return response entity
   */
  @PutMapping("/add-passport")
  public ResponseEntity<RussianPassport> addPassportStudent(@RequestBody StudentDto student) {

    log.info("trying to add RussianPassport: " + student.getPassport());

    return ResponseEntity
        .ok()
        .body(editStudent.addPassport(student));
  }
}
