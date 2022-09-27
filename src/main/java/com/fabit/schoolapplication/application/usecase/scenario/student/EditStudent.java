package com.fabit.schoolapplication.application.usecase.scenario.student;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.application.usecase.scenario.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import java.time.Clock;
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
    if (RussianPassport.isValidAge(
        studentDto.getBirthCertificate().getBirthday(),
        Clock.systemUTC()
    )) {
      studentService.save(studentDto, passport);

      return passport;
    } else {
      throw new IllegalArgumentException("Не валидный возраст");
    }
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