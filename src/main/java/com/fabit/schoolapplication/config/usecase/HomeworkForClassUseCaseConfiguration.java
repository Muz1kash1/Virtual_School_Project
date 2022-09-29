package com.fabit.schoolapplication.config.usecase;

import com.fabit.schoolapplication.application.usecase.access.homeworkforclass.HomeworkForClassService;
import com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass.ChangeHomeworkTaskUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass.CreateHomeworkForClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass.DeleteHomeworkForClassUseCase;
import com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass.GetHomeworkForClassUseCase;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.HomeworkForClassServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.HomeworkForClassMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HomeworkForClassUseCaseConfiguration {
  @Bean
  HomeworkForClassServiceImpl homeworkForClassService(
      HomeworkForClassRepository homeworkForClassRepository,
      HomeworkForClassMapper homeworkForClassMapper) {
    return new HomeworkForClassServiceImpl(homeworkForClassMapper, homeworkForClassRepository);
  }

  @Bean
  ChangeHomeworkTaskUseCase changeHomeworkTask(HomeworkForClassService homeworkForClassService) {
    return new ChangeHomeworkTaskUseCase(homeworkForClassService);
  }

  @Bean
  CreateHomeworkForClassUseCase createHomeworkForClass(HomeworkForClassService homeworkForClassService) {
    return new CreateHomeworkForClassUseCase(homeworkForClassService);
  }

  @Bean
  DeleteHomeworkForClassUseCase deleteHomeworkForClass(HomeworkForClassService homeworkForClassService) {
    return new DeleteHomeworkForClassUseCase(homeworkForClassService);
  }

  @Bean
  GetHomeworkForClassUseCase getHomeworkForClass(HomeworkForClassService homeworkForClassService) {
    return new GetHomeworkForClassUseCase(homeworkForClassService);
  }
}
