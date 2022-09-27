package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.Passport;
import com.fabit.schoolapplication.infrastructure.mapper.StudentMapperServiceImpl;
import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.infrastructure.ui.controller.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.event.StudentDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapperServiceImpl studentMapperService;
  private final ApplicationEventPublisher eventPublisher;


  /**
   * Удалить ученика с идентификатором id.
   *
   * @param id - идентификатор ученика
   */
  @Override
  public void deleteStudent(long id) {
    studentRepository.deleteById(id);
    eventPublisher.publishEvent(new StudentDeletedEvent(this, id));
  }

  @Override
  public boolean findBySnils(String snils) {
    return studentRepository.findBySnils(snils) != null;
  }

  @Override
  public void deleteAll() {
    studentRepository.deleteAll();
  }

  @Override
  public void save(StudentDto studentDto, BirthCertificate birthCertificate) {
    StudentEntity studentEntity
        = studentRepository.findBySnils(studentDto.getSnils().getNumberView());

    studentEntity.setBirthCertificate(birthCertificate.toString());
  }

  @Override
  public void save(Student student) {
     studentRepository.save(studentMapperService.mapToStudentEntity(student));
  }

  /**
   * Добавление паспорта уже существующему ученику.
   *
   * @param studentDto - StudentDTO
   */
  @Override
  public void save(Student studentDto, Passport passport) {
    StudentEntity studentEntity
        = studentRepository.findBySnils(studentDto.getSnils().getNumberView());
    studentEntity.setPassport(passport.toString());

    studentRepository.save(studentEntity);
  }

  @Override
  public void save(Student student, Snils snils) {
    StudentEntity studentEntity
        = studentRepository.findByBirthCertificate(student.getBirthCertificate().toString());

    studentEntity.setSnils(snils.getNumberView());
  }

  @Override
  public void save(Student student, BirthCertificate birthCertificate) {
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);
    studentRepository.save(studentEntity);
  }
}