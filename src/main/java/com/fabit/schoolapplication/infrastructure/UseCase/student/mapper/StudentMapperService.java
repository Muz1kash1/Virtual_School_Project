package com.fabit.schoolapplication.infrastructure.UseCase.student.mapper;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.controller.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapperService {
  default StudentEntity mapToStudentEntity(Student student) {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setName(student.getName());
    studentEntity.setBirthday(student.getBirthday());
    if (student.getPassport() != null) {
      studentEntity.setPassport(
          student.getPassport().getNumber() + "" + student.getPassport().getSerial());
    }
    studentEntity.setBirthCertificate(student.getBirthCertificate().getNumber() + " " +
        student.getBirthCertificate().getSerial());
    return studentEntity;
  }

  default Student mapToStudent(StudentDto studentDto) {

    Student student =
        Student.of(StudentId.of(1), studentDto.getName(), mapToSnils(studentDto.getSnils()),
            mapToBirthCertificate(studentDto.getBirthCertificate()), studentDto.getBirthday());

    return student;
  }

  default String map(BirthCertificate value) {
    return value.toString();
  }

  default String map(Passport value) {
    return value.toString();
  }

  default String map(Snils value) {
    return value.toString();
  }

  default Snils mapToSnils(String value) {
    return Snils.of(value.trim());
  }

  default BirthCertificate mapToBirthCertificate(String value) {
    String[] strings = value.trim().split(" ");
    return BirthCertificate.of(strings[0], strings[1]);
  }

  default Passport mapToPassport(String value) {
    String[] strings = value.trim().split(" ");
    return Passport.of(strings[0], strings[1]);
  }
}