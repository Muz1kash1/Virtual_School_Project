package com.fabit.schoolapplication.infrastructure.usecase.student;

import com.fabit.schoolapplication.domain.student.Student;
import com.fabit.schoolapplication.domain.student.event.StudentCreatedEvent;
import com.fabit.schoolapplication.infrastructure.controller.student.dto.StudentDto;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.student.StudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import com.fabit.schoolapplication.infrastructure.usecase.student.mapper.StudentMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * сервис создания студента
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateStudent {
  final StudentRepository studentRepository;
  final StudentMapperService studentMapperService;

  /**
   * Создание студента.
   *
   * @param studentDto student dto
   */
  public StudentEntity execute(StudentDto studentDto) {
    Student student = studentMapperService.mapToStudent(studentDto);
    StudentEntity studentEntity = studentMapperService.mapToStudentEntity(student);
    if (studentRepository.findBySnils(studentDto.getSnils().toString()) == null) {
      studentRepository.save(studentEntity);
    }
    return studentEntity;
  }

  // TO DO перенести в отдельный класс и добавить логику
  @EventListener
  public void studentCreatedEvent(StudentCreatedEvent event) {
    log.info("StudentCreatedEvent...");
    log.info("Студент с именем " + event.getStudent().getName().getName() + " был успешно создан");
  }
}