package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.infrastructure.controller.student.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.usecase.student.mapper.StudentMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Сервис редактирования студента
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EditStudent {
  final StudentRepository studentRepository;
  final StudentMapperService studentMapperService;

  /**
   * Добавление паспорта.
   *
   * @param studentDto the student dto
   */
  public void addPassport(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    student.addPassport(studentMapperService.mapToPassport(studentDto.getPassport()));
    StudentEntity studentEntity = studentRepository.findBySnils(studentDto.getSnils());
    studentEntity.setPassport(studentDto.getPassport());
    studentRepository.save(studentEntity);
  }

  /**
   * Изменение свидетельства о рождении.
   *
   * @param studentDto student dto
   */
  public StudentEntity changeBirthCertificate(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    student.changeBirthCertificate(
        studentMapperService.mapToBirthCertificate(studentDto.getBirthCertificate()));
    StudentEntity studentEntity = studentRepository.findBySnils(studentDto.getSnils());
    studentEntity.setBirthCertificate(studentDto.getBirthCertificate());
    studentRepository.save(studentEntity);
    return studentEntity;
  }

  /**
   * Изменение СНИЛСа
   *
   * @param studentDto student dto
   */
  public StudentEntity changeSnils(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    student.changeSnils(studentMapperService.mapToSnils(studentDto.getSnils()));
    StudentEntity studentEntity = studentRepository.findBySnils(studentDto.getSnils());
    studentEntity.setSnils(studentDto.getSnils());
    studentRepository.save(studentEntity);
    return studentEntity;
  }

  // TO DO перенести в отдельный класс и добавить логику
  @EventListener
  public void StudentChangedInfoEvent(StudentChangedInfoEvent event) {
    log.info("StudentChangedInfoEvent...");
    log.info("Студент с именем " + event.getStudent().getName() + " был успешно изменен");
  }
}