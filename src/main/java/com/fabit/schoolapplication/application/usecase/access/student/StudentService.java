package com.fabit.schoolapplication.application.usecase.access.student;

import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.Passport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;

public interface StudentService {

  void save(Student student);

  void save(Student student, BirthCertificate birthCertificate);

  void save(Student student, Passport passport);

  void saveBirthCertificate(Student student, BirthCertificate birthCertificate);

  void saveSnils(Student student, Snils snils);

  void deleteStudent(long id);

  boolean findBySnils(String toString);

  void deleteAll();
}