package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.event.StudentCreatedEvent;
import com.fabit.schoolapplication.infrastructure.usecase.student.mapper.StudentMapperService;
import com.fabit.schoolapplication.infrastructure.controller.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateStudent {
  final StudentRepository studentRepository;
  final StudentMapperService studentMapperService;

  public void createStudent(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);
    studentRepository.save(studentEntity);
  }
// TO DO перенести в отдельный класс
  @EventListener
  public void studentCreatedEvent(StudentCreatedEvent event) {
    log.info("StudentCreatedEvent...");
    log.info("Студент с именем " + event.getStudent().getName() + " был успешно создан");
  }
}