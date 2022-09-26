package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.mapper.StudentMapperService;
import com.fabit.schoolapplication.application.usecase.acces.student.StudentService;
import com.fabit.schoolapplication.application.usecase.scenarious.student.dto.StudentDto;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.BirthCertificate;
import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.infrastructure.event.StudentDeletedEvent;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import java.util.List;

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
   * @return русский паспорт
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