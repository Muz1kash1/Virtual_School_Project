package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.application.usecase.scenario.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.event.StudentDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentMapperService studentMapperService;
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
  public List<StudentEntity> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public void save(StudentDto studentDto, BirthCertificate birthCertificate) {
    StudentEntity studentEntity
        = studentRepository.findBySnils(studentDto.getSnils().getNumberView());

    studentEntity.setBirthCertificate(birthCertificate.toString());
  }

  @Override
  public void save(StudentDto studentDto) {

  }

  /**
   * Добавление паспорта уже существующему ученику.
   *
   * @param studentDto - StudentDTO
   */
  @Override
  public void save(StudentDto studentDto, RussianPassport passport) {
    StudentEntity studentEntity
        = studentRepository.findBySnils(studentDto.getSnils().getNumberView());
    studentEntity.setPassport(passport.toString());

    studentRepository.save(studentEntity);
  }

  @Override
  public void save(StudentDto studentDto, Snils snils) {
    StudentEntity studentEntity
        = studentRepository.findByBirthCertificate(studentDto.getBirthCertificate().toString());

    studentEntity.setSnils(snils.getNumberView());
  }

  @Override
  public void save(Student student) {
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);
    studentRepository.save(studentEntity);
  }
}