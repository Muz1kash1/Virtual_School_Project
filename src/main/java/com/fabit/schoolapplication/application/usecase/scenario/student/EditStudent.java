package com.fabit.schoolapplication.application.usecase.scenario.student;

import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.Passport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import lombok.RequiredArgsConstructor;

/**
 * Юзкейс редактирования ученика.
 */
@RequiredArgsConstructor
public class EditStudent {

  private final StudentService studentService;

  /**
   * Добавление паспорта уже существующему ученику.
   *
   * @param student - StudentDTO
   * @return русский паспорт
   */
  public Passport addPassport(Student student) {
    Passport passport = student.getPassport();
    studentService.save(student, passport);

    return passport;
  }

  /**
   * Изменение свидетельства о рождении уже существующего ученика.
   *
   * @param student - студент
   * @return свид. о рождении
   */
  public BirthCertificate changeBirthCertificate(Student student) {
    BirthCertificate birthCertificate = student.getBirthCertificate();
    studentService.saveBirthCertificate(student, birthCertificate);
    return birthCertificate;
  }

  /**
   * Изменение СНИЛСа уже существующего ученика.
   *
   * @param student - StudentDTO
   * @return СНИЛС
   */
  public Snils changeSnils(Student student) {
    Snils snils = student.getSnils();
    studentService.saveSnils(student, snils);
    return snils;
  }

}