package com.fabit.schoolapplication.application.usecase.scenario.student;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.application.usecase.scenario.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import lombok.RequiredArgsConstructor;

/**
 * Юзкейс редактирования ученика.
 */
@RequiredArgsConstructor
public class EditStudent {

  private final StudentService studentService;
  private final StudentMapperService studentMapperService;

  /**
   * Добавление паспорта уже существующему ученику.
   *
   * @param studentDto - StudentDTO
   * @return русский паспорт
   */
  public RussianPassport addPassport(StudentDto studentDto) {
    RussianPassport passport = studentMapperService.mapToPassport(studentDto.getPassport());
    studentService.save(studentDto, passport);

    return passport;
  }

  /**
   * Изменение свидетельства о рождении уже существующего ученика.
   *
   * @param studentDto - StudentDTO
   * @return свид. о рождении
   */
  public BirthCertificate changeBirthCertificate(StudentDto studentDto) {
    BirthCertificate birthCertificate
      = studentMapperService.mapToBirthCertificate(studentDto.getBirthCertificate());

    studentService.save(studentDto, birthCertificate);
    return birthCertificate;
  }

  /**
   * Изменение СНИЛСа уже существующего ученика.
   *
   * @param studentDto - StudentDTO
   * @return СНИЛС
   */
  public Snils changeSnils(StudentDto studentDto) {
    Snils snils
      = studentMapperService.mapToSnils(studentDto.getSnils());
    studentService.save(studentDto, snils);
    return snils;
  }
}