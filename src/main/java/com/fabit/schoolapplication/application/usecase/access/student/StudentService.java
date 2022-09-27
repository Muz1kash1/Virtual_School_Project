package com.fabit.schoolapplication.application.usecase.access.student;

import com.fabit.schoolapplication.application.usecase.scenario.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import java.util.List;

public interface StudentService {

  void save(StudentDto studentDto);

  void save(Student student);

  void save(final StudentDto studentDto, RussianPassport passport);

  void save(StudentDto studentDto, Snils snils);

  void save(StudentDto studentDto, BirthCertificate birthCertificate);

  void deleteStudent(long id);

  boolean findBySnils(String toString);

  void deleteAll();

  List<StudentEntity> findAll();
}
