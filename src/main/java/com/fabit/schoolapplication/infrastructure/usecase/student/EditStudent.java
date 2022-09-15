package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.usecase.student.mapper.StudentMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Сервис редактирования студента
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EditStudent {
  final StudentRepository studentRepository;
  final StudentMapperService studentMapperService;

  /**
   * Добавление паспорта.
   *
   * @param studentDto the student dto
   */
  public StudentEntity addPassport(StudentDto studentDto) {
    Passport passport = studentMapperService.mapToPassport(studentDto.getPassport());
    if (passport.isValidAge(studentDto.getBirthday())) {
      StudentEntity studentEntity =
          studentRepository.findBySnils(studentDto.getSnils().getNumberView());
      studentEntity.setPassport(passport.toString());
      studentRepository.save(studentEntity);
      return studentEntity;
    } else {
      throw new IllegalArgumentException("Не валидный возраст");
    }
  }

  /**
   * Изменение свидетельства о рождении.
   *
   * @param studentDto student dto
   */
  public StudentEntity changeBirthCertificate(StudentDto studentDto) {
    BirthCertificate birthCertificate =
        studentMapperService.mapToBirthCertificate(studentDto.getBirthCertificate());
    StudentEntity studentEntity =
        studentRepository.findBySnils(studentDto.getSnils().getNumberView());
    studentEntity.setBirthCertificate(birthCertificate.toString());
    studentRepository.save(studentEntity);
    return studentEntity;
  }

  /**
   * Изменение СНИЛСа
   *
   * @param studentDto student dto
   */
  public StudentEntity changeSnils(StudentDto studentDto) {
    Snils snils = studentMapperService.mapToSnils(studentDto.getSnils());
    StudentEntity studentEntity =
        studentRepository.findByBirthCertificate(studentDto.getBirthCertificate().toString());
    studentEntity.setSnils(snils.getNumberView());
    studentRepository.save(studentEntity);
    return studentEntity;
  }

  // TO DO перенести в отдельный класс и добавить логику
  @EventListener
  public void StudentChangedInfoEvent(StudentChangedInfoEvent event) {
    log.info("StudentChangedInfoEvent...");
    log.info("Студент с именем " + event.getStudent().getName() + " был успешно изменен");
  }
}