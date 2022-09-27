package com.fabit.schoolapplication.config.usecase;

import com.fabit.schoolapplication.application.usecase.access.schoolclass.SchoolClassService;
import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.AddStudentToClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.CreateSchoolClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.DeleteSchoolClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.GetSchoolClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.schoolclass.RemoveStudentFromClassUseCase;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.SchoolClassServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.SchoolClassRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentInClassRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolClassUseCaseConfiguration {

  @Bean
  public SchoolClassService schoolClassService(SchoolClassRepository schoolClassRepo,
                                               StudentInClassRepository studentInClassRepo) {
    return new SchoolClassServiceImpl(schoolClassRepo, studentInClassRepo);
  }

  @Bean
  public GetSchoolClassUseCase getSchoolClassUseCase(SchoolClassService service) {
    return new GetSchoolClassUseCase(service);
  }

  @Bean
  public CreateSchoolClassUseCase createSchoolClassUseCase(SchoolClassService service) {
    return new CreateSchoolClassUseCase(service);
  }

  @Bean
  public DeleteSchoolClassUseCase deleteSchoolClassUseCase(SchoolClassService service) {
    return new DeleteSchoolClassUseCase(service);
  }

  @Bean
  public AddStudentToClassUseCase addStudentToClassUseCase(SchoolClassService service) {
    return new AddStudentToClassUseCase(service);
  }

  @Bean
  public RemoveStudentFromClassUseCase removeStudentFromClassUseCase(SchoolClassService service) {
    return new RemoveStudentFromClassUseCase(service);
  }

}
