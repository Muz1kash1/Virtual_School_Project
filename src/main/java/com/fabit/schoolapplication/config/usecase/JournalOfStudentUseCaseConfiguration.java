package com.fabit.schoolapplication.config.usecase;

import com.fabit.schoolapplication.application.usecase.access.journalofstudent.JournalByDisciplineService;
import com.fabit.schoolapplication.application.usecase.access.journalofstudent.JournalOfStudentService;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.JournalByDisciplineScenario;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.JournalOfStudentScenario;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.JournalByDisciplineServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.impl.JournalOfStudentServiceImpl;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.JournalPersistenceMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.AchievementRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.JournalByDisciplineRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.JournalOfStudentRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JournalOfStudentUseCaseConfiguration {
  @Bean
  public JournalOfStudentService journalOfStudentService(JournalOfStudentRepository journalOfStudentRepository,
                                                         JournalPersistenceMapper journalPersistenceMapper,
                                                         StudentRepository studentRepository) {
    return new JournalOfStudentServiceImpl(journalOfStudentRepository, journalPersistenceMapper, studentRepository);
  }

  @Bean
  public JournalByDisciplineService journalByDisciplineService(
    AchievementRepository achievementRepository,
    JournalOfStudentRepository journalOfStudentRepository,
    JournalPersistenceMapper journalPersistenceMapper,
    JournalByDisciplineRepository journalByDisciplineRepository) {
    return new JournalByDisciplineServiceImpl(
      achievementRepository,
      journalOfStudentRepository,
      journalPersistenceMapper,
      journalByDisciplineRepository);
  }

  @Bean
  public JournalByDisciplineScenario journalByDisciplineScenario(
    JournalByDisciplineService journalByDisciplineService) {
    return new JournalByDisciplineScenario(journalByDisciplineService);
  }

  @Bean
  public JournalOfStudentScenario journalOfStudentScenario(JournalOfStudentService journalOfStudentService) {
    return new JournalOfStudentScenario(journalOfStudentService);
  }
}
