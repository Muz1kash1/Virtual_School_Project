package com.fabit.schoolapplication.config.usecase;

import com.fabit.schoolapplication.application.usecase.access.teacher.TeacherService;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.CreateTeacher;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.DeleteTeacher;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.EditTeacher;
import com.fabit.schoolapplication.application.usecase.scenario.teacher.GetTeacher;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.TeacherServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.TeacherPersistenceMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherUseCaseConfiguration {
  @Bean
  public TeacherPersistenceMapper teacherServiceMapper() {
    return new TeacherPersistenceMapper();
  }

  @Bean
  public TeacherService teacherService(TeacherPersistenceMapper teacherPersistenceMapper,
                                       TeacherRepository teacherRepository,
                                       ApplicationEventPublisher eventPublisher) {

    return new TeacherServiceImpl(
      teacherPersistenceMapper,
      teacherRepository,
      eventPublisher
    );
  }

  @Bean
  public CreateTeacher createTeacher(TeacherService teacherService) {
    return new CreateTeacher(teacherService);
  }

  @Bean
  public DeleteTeacher deleteTeacher(TeacherService teacherService) {
    return new DeleteTeacher(teacherService);
  }

  @Bean
  public EditTeacher editTeacher(TeacherService teacherService) {
    return new EditTeacher(teacherService);
  }

  @Bean
  public GetTeacher getTeacher(TeacherService teacherService) {
    return new GetTeacher(teacherService);
  }

}
