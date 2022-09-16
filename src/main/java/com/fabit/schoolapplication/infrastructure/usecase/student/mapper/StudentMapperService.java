package com.fabit.schoolapplication.infrastructure.usecase.student.mapper;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.BirthCertificateDto;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.FullNameDto;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.PassportDto;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.SnilsDto;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class StudentMapperService {
  public StudentEntity mapToStudentEntity(Student student) {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setName(student.getFullName().toString());
    studentEntity.setBirthday(mapToBirthdayDate(student.getBirthday()));
    studentEntity.setSnils(student.getSnils().getNumberView());
    if (student.getPassport() != null) {
      studentEntity.setPassport(student.getPassport().toString());
    }
    studentEntity.setBirthCertificate(student.getBirthCertificate().toString());
    return studentEntity;
  }

  public Student mapToStudent(StudentDto studentDto) {
    Student student = Student.of(StudentId.of(1), mapToFullName(studentDto.getName()),
        mapToSnils(studentDto.getSnils()), mapToBirthCertificate(studentDto.getBirthCertificate()),
        studentDto.getBirthday());

    return student;
  }

  public String mapToBirthdayDate(LocalDate date) {
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
  }

  public Snils mapToSnils(SnilsDto value) {
    return Snils.of(value.getNumberView());
  }

  public FullName mapToFullName(FullNameDto value) {
    return FullName.of(value.getName(), value.getSurname(), value.getPatronymic());
  }

  public BirthCertificate mapToBirthCertificate(BirthCertificateDto value) {
    return BirthCertificate.of(value.getSerial(), value.getNumber());
  }

  public Passport mapToPassport(PassportDto value) {
    return Passport.of(value.getSerial(), value.getNumber());
  }
}