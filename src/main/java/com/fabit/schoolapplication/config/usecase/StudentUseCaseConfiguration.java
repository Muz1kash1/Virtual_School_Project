package com.fabit.schoolapplication.config.usecase;

import com.fabit.schoolapplication.application.usecase.access.student.StudentService;
import com.fabit.schoolapplication.application.usecase.scenario.student.CreateStudent;
import com.fabit.schoolapplication.application.usecase.scenario.student.DeleteStudent;
import com.fabit.schoolapplication.application.usecase.scenario.student.EditStudent;
import com.fabit.schoolapplication.infrastructure.mapper.StudentMapperServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.StudentServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentUseCaseConfiguration {

  @Bean
  public StudentMapperServiceImpl studentMapperServiceImpl(StudentRepository studentRepository) {
    return new StudentMapperServiceImpl(studentRepository);
  }

  @Bean
  public StudentService studentService(StudentRepository studentRepository,
                                       StudentMapperServiceImpl studentMapperService,
                                       ApplicationEventPublisher applicationEventPublisher) {
    return new StudentServiceImpl(
      studentRepository,
      studentMapperService,
      applicationEventPublisher
    );
  }

  @Bean
  public CreateStudent createStudent(StudentService studentService) {
    return new CreateStudent(studentService);
  }

  @Bean
  public DeleteStudent deleteStudent(StudentService studentService) {
    return new DeleteStudent(studentService);
  }

  @Bean
  public EditStudent editStudent(StudentService studentService) {
    return new EditStudent(studentService);
  }
}