package com.fabit.schoolapplication.infrastructure.usecase.student.mapper;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.controller.student.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentMapperService {
  public StudentEntity mapToStudentEntity(Student student) {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setName(student.getName());
    studentEntity.setBirthday(student.getBirthday());
    studentEntity.setSnils(map(student.getSnils()));
    if (student.getPassport() != null) {
      studentEntity.setPassport(
          student.getPassport().getNumber() + " " + student.getPassport().getSerial());
    }
    studentEntity.setBirthCertificate(student.getBirthCertificate().getNumber() + " " +
        student.getBirthCertificate().getSerial());
    return studentEntity;
  }

  public Student mapToStudent(StudentDto studentDto) {

    Student student =
        Student.of(StudentId.of(1), studentDto.getName(), mapToSnils(studentDto.getSnils()),
            mapToBirthCertificate(studentDto.getBirthCertificate()), studentDto.getBirthday());

    return student;
  }

  public String map(BirthCertificate value) {
    return value.toString();
  }

  public String map(Passport value) {
    return value.toString();
  }

  public String map(Snils value) {
    return value.toString();
  }

  public Snils mapToSnils(String value) {
    return Snils.of(value.trim());
  }

  public BirthCertificate mapToBirthCertificate(String value) {
    String[] strings = value.trim().split(" ");
    return BirthCertificate.of(strings[0], strings[1]);
  }

  public Passport mapToPassport(String value) {
    String[] strings = value.trim().split(" ");
    return Passport.of(strings[0], strings[1]);
  }
}