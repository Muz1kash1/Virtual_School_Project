package com.fabit.schoolapplication.infrastructure.mapper;

import com.fabit.schoolapplication.domain.generalvalueobject.fullname.FullName;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.BirthCertificateDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.StudentDto;
import java.time.Clock;
import lombok.RequiredArgsConstructor;

/**
 * The type Student mapper service.
 */
@RequiredArgsConstructor
public class StudentMapperServiceImpl {

  final StudentRepository studentRepository;

  /**
   * Маппинг доменного ученика в persistent сущность.
   *
   * @param student - доменный ученик
   * @return StudentEntity
   */
  public StudentEntity mapToStudentEntity(Student student) {

    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setId(student.getId().getValue());
    studentEntity.setName(student.getFullName().toString());
    studentEntity.setSnils(student.getSnils().getNumberView());

    if (student.getPassport() != null) {
      studentEntity.setPassport(student.getPassport().toString());
    }

    studentEntity.setBirthCertificate(student.getBirthCertificate().toString());

    return studentEntity;
  }

  /**
   * Маппинг ДТО ученика в доменную модель.
   *
   * @param studentDto - ученик
   * @return Student
   */
  public Student mapToStudent(StudentDto studentDto) {

    if (studentDto.getPassport() == null) {
      Long id = studentRepository.getNextId();
      return Student.of(
          StudentId.of(id),
          mapToFullName(studentDto.getName()),
          mapToSnils(studentDto.getSnils()),
          mapToBirthCertificate(studentDto.getBirthCertificate())
      );
    }

    return Student.of(
        StudentId.of(studentRepository.getNextId()),
        mapToFullName(studentDto.getName()),
        mapToSnils(studentDto.getSnils()),
        mapToBirthCertificate(studentDto.getBirthCertificate()),
        mapToPassport(studentDto.getPassport())
    );
  }

  /**
   * Маппинг ДТО СНИЛС в доменную модель.
   *
   * @param snilsDto - СНИЛС
   * @return snils
   */
  public Snils mapToSnils(SnilsDto snilsDto) {
    return Snils.of(snilsDto.getNumberView());
  }

  /**
   * Маппинг ДТО ФИО в доменную модель.
   *
   * @param value - ФИО
   * @return ФИО
   */
  public FullName mapToFullName(FullNameDto value) {
    return FullName.of(value.getName(), value.getSurname(), value.getPatronymic());
  }

  /**
   * Маппинг ДТО Свид. о рожд. в доменную модель.
   *
   * @param birthCertificateDto - СВид. о рожд.
   * @return свид. о рожд.
   */
  public BirthCertificate mapToBirthCertificate(BirthCertificateDto birthCertificateDto) {
    return BirthCertificate.of(
        birthCertificateDto.getSerial(),
        birthCertificateDto.getNumber(),
        birthCertificateDto.getBirthday(),
        Clock.systemUTC()
    );
  }

  /**
   * Маппинг ДТО паспорта в доменную модель.
   *
   * @param passportDto - паспорт
   * @return русский паспорт
   */
  public RussianPassport mapToPassport(PassportDto passportDto) {
    return RussianPassport.of(
        passportDto.getSerial(),
        passportDto.getNumber(),
        passportDto.getBirthday(),
        Clock.systemUTC()
    );
  }

}