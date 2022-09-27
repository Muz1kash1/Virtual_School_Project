package com.fabit.schoolapplication.application.usecase.access.student;

import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.Passport;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;

public interface StudentService {

  void save(Student studentDto);

  void save(Student student, BirthCertificate birthCertificate);

  void save(Student studentDto, Passport passport);

  void save(Student studentDto, Snils snils);

  void save(StudentDto studentDto, BirthCertificate birthCertificate);

  void deleteStudent(long id);

  boolean findBySnils(String toString);

  void deleteAll();
}