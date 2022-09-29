package com.fabit.schoolapplication.config.usecase;

import com.fabit.schoolapplication.application.usecase.access.loadedhomework.LoadedHomeworkService;
import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.CompleteHomeworkUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.loadedhomework.GetLoadedHomeworkUseCase;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.LoadedHomeworkServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.LoadedHomeworkMapperService;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.LoadedHomeworkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadedHomeworkUseCaseConfiguration {
  @Bean
  LoadedHomeworkServiceImpl loadedHomeworkServiceImpl(
      LoadedHomeworkRepository loadedHomeworkRepository,
      LoadedHomeworkMapperService loadedHomeworkMapperService) {
    return new LoadedHomeworkServiceImpl(loadedHomeworkRepository, loadedHomeworkMapperService);
  }

  @Bean
  GetLoadedHomeworkUseCase getLoadedHomework(LoadedHomeworkService loadedHomeworkService) {
    return new GetLoadedHomeworkUseCase(loadedHomeworkService);
  }

  @Bean
  CompleteHomeworkUseCase completeHomework(LoadedHomeworkService loadedHomeworkService) {
    return new CompleteHomeworkUseCase(loadedHomeworkService);
  }
}
