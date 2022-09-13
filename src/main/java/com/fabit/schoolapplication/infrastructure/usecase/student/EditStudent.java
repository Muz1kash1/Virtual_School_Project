package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.infrastructure.controller.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.usecase.student.mapper.StudentMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditStudent {
  final StudentRepository studentRepository;
  final StudentMapperService studentMapperService;

  public void addPassport(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    student.addPassport(studentMapperService.mapToPassport(studentDto.getPassport()));
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);
    studentRepository.save(studentEntity);
  }

  public void changeBirthCertificate(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    student.changeBirthCertificate(
        studentMapperService.mapToBirthCertificate(studentDto.getBirthCertificate()));
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);
    studentRepository.save(studentEntity);
  }

  // TO DO перенести в отдельный класс
  @EventListener
  public void StudentChangedInfoEvent(StudentChangedInfoEvent event) {
    log.info("StudentCreatedEvent...");
    log.info("Студент с именем " + event + "был успешно изменен");
  }
}