package com.fabit.schoolapplication.infrastructure.usecase.student.mapper;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.RussianPassport;
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
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentMapperService {
  final StudentRepository studentRepository;

  public StudentEntity mapToStudentEntity(Student student) {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setName(student.getFullName().toString());
    studentEntity.setSnils(student.getSnils().getNumberView());
    if (student.getPassport() != null) {
      studentEntity.setPassport(student.getPassport().toString());
    }
    studentEntity.setBirthCertificate(student.getBirthCertificate().toString());
    return studentEntity;
  }

  public Student mapToStudent(StudentDto studentDto) {
    Student student =
        Student.of(StudentId.of(studentRepository.getNextId()), mapToFullName(studentDto.getName()),
            mapToSnils(studentDto.getSnils()),
            mapToBirthCertificate(studentDto.getBirthCertificate()));

    return student;
  }

  public Snils mapToSnils(SnilsDto value) {
    return Snils.of(value.getNumberView());
  }

  public FullName mapToFullName(FullNameDto value) {
    return FullName.of(value.getName(), value.getSurname(), value.getPatronymic());
  }

  public BirthCertificate mapToBirthCertificate(BirthCertificateDto value) {
    return BirthCertificate.of(value.getSerial(), value.getNumber(), value.getBirthday());
  }

  public RussianPassport mapToPassport(PassportDto value) {
    return RussianPassport.of(value.getSerial(), value.getNumber(), value.getBirthday());
  }
}