package com.fabit.schoolapplication.application.usecase.virtualschool.student;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.infrastructure.controller.virtualschool.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import java.time.Clock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Юзкейс редактирования ученика.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EditStudent {
  final StudentRepository studentRepository;
  final StudentMapperService studentMapperService;

  /**
   * Добавление паспорта уже существующему ученику.
   *
   * @param studentDto - StudentDTO
   */
  public StudentEntity addPassport(StudentDto studentDto) {

    RussianPassport passport = studentMapperService.mapToPassport(studentDto.getPassport());
    if (RussianPassport.isValidAge(studentDto.getBirthCertificate().getBirthday(),
      Clock.systemUTC())) {

      StudentEntity studentEntity
        = studentRepository.findBySnils(studentDto.getSnils().getNumberView());
      studentEntity.setPassport(passport.toString());

      studentRepository.save(studentEntity);

      return studentEntity;
    } else {
      throw new IllegalArgumentException("Не валидный возраст");
    }
  }

  /**
   * Изменение свидетельства о рождении уже существующего ученика.
   *
   * @param studentDto - StudentDTO
   */
  public StudentEntity changeBirthCertificate(StudentDto studentDto) {
    BirthCertificate birthCertificate
      = studentMapperService.mapToBirthCertificate(studentDto.getBirthCertificate());
    StudentEntity studentEntity
      = studentRepository.findBySnils(studentDto.getSnils().getNumberView());

    studentEntity.setBirthCertificate(birthCertificate.toString());

    studentRepository.save(studentEntity);
    return studentEntity;
  }

  /**
   * Изменение СНИЛСа уже существующего ученика.
   *
   * @param studentDto - StudentDTO
   */
  public StudentEntity changeSnils(StudentDto studentDto) {
    Snils snils
      = studentMapperService.mapToSnils(studentDto.getSnils());
    StudentEntity studentEntity
      = studentRepository.findByBirthCertificate(studentDto.getBirthCertificate().toString());

    studentEntity.setSnils(snils.getNumberView());

    studentRepository.save(studentEntity);
    return studentEntity;
  }
}