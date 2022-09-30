package com.fabit.schoolapplication.infrastructure.persisnence.impl;

import com.fabit.schoolapplication.application.usecase.access.journalofstudent.JournalByDisciplineService;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementForStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementOfStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllAchievementsOfStudentByDisciplineDto;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.journalofstudent.agregate.JournalOfStudent;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalByDisciplineId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.AchievementEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalOfStudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.mapper.JournalPersistenceMapper;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.AchievementRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.JournalByDisciplineRepository;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.JournalOfStudentRepository;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class JournalByDisciplineServiceImpl implements JournalByDisciplineService {
  private final AchievementRepository achievementRepository;
  private final JournalOfStudentRepository journalOfStudentRepository;
  private final JournalPersistenceMapper journalPersistenceMapper;
  private final JournalByDisciplineRepository journalByDisciplineRepository;

  @Override
  public void save(long studentId, String discipline) {
    JournalOfStudentEntity journalOfStudentEntity
      = journalOfStudentRepository.findByStudentId(
      studentId
    );
    JournalOfStudent journalOfStudent
      = journalPersistenceMapper.mapEntityJournalOfStudentToDomain(
      journalOfStudentEntity
    );

    journalOfStudent.addJournalByDiscipline(
      JournalByDisciplineId.of(
        journalOfStudentRepository.getNextId()
      ),
      Discipline.parse(discipline)
    );

    journalOfStudentEntity
      = journalPersistenceMapper.mapDomainJournalOfStudentToEntity(
      journalOfStudent
    );

    journalOfStudentRepository.save(journalOfStudentEntity);
  }

  @Override
  public void delete(long studentId, String discipline) {

    journalByDisciplineRepository.deleteByJournalOfStudentEntityStudentId(
      studentId, discipline
    );
  }

  @Override
  public AllAchievementsOfStudentByDisciplineDto getAllByStudentAndDiscipline(
    long studentId, String discipline
  ) {
    List<AchievementEntity> achievementEntities =
      achievementRepository.findAllByStudentIdAndDiscipline(
        studentId, discipline
      );

    AllAchievementsOfStudentByDisciplineDto allAchievementsOfStudentByDisciplineDto
      = new AllAchievementsOfStudentByDisciplineDto();

    allAchievementsOfStudentByDisciplineDto.setAchievements(
      journalPersistenceMapper.mapAchievementsEntityListToDtoList(
        achievementEntities
      ));
    return allAchievementsOfStudentByDisciplineDto;
  }

  @Override
  public AchievementOfStudentDto getByDisciplineAndDateOfLesson(
    long studentId, String discipline, LocalDate dateOfLesson
  ) {
    AchievementEntity achievementEntity
      = achievementRepository.findByDisciplineAndDateOfLessonAndStudentId(
      studentId, discipline, dateOfLesson
    );

    AchievementOfStudentDto achievement = new AchievementOfStudentDto();

    achievement.setAchievement(
      achievementEntity.getValue()
    );
    achievement.setDateOfLesson(
      achievementEntity.getDateOfLesson()
    );

    return achievement;
  }

  @Override
  public void addAchievement(
    long studentId, AchievementForStudentDto achievement
  ) {

    JournalOfStudentEntity journalOfStudentEntity
      = journalOfStudentRepository.findByStudentId(studentId);

    JournalOfStudent journalOfStudent
      = journalPersistenceMapper.mapEntityJournalOfStudentToDomain(
      journalOfStudentEntity
    );

    journalOfStudent.addAchievementForStudentByDiscipline(
      Discipline.parse(achievement.getDiscipline()),
      achievement.getAchievement(),
      achievement.getDateOfLesson()
    );

    journalOfStudentEntity
      = journalPersistenceMapper.mapDomainJournalOfStudentToEntity(
      journalOfStudent
    );

    journalOfStudentRepository.save(journalOfStudentEntity);
  }

  @Override
  public void changeAchievement(
    long studentId, AchievementForStudentDto achievement
  ) {

    JournalOfStudentEntity journalOfStudentEntity
      = journalOfStudentRepository.findByStudentId(studentId);

    JournalOfStudent journalOfStudent =
      journalPersistenceMapper.mapEntityJournalOfStudentToDomain(
        journalOfStudentEntity
      );

    journalOfStudent.changeAchievementByDisciplineAndDateOfLesson(
      Discipline.parse(achievement.getDiscipline()),
      achievement.getAchievement(),
      achievement.getDateOfLesson()
    );
    journalOfStudentEntity
      = journalPersistenceMapper.mapDomainJournalOfStudentToEntity(
      journalOfStudent
    );

    journalOfStudentRepository.save(journalOfStudentEntity);
  }

  @Override
  public void deleteAchievement(
    long studentId, AchievementForStudentDto achievement
  ) {

    achievementRepository.deleteByDisciplineAndDateOfLessonAndStudentIdAndValue(
      studentId,
      achievement.getDiscipline(),
      achievement.getDateOfLesson(),
      achievement.getAchievement()
    );
  }
}
