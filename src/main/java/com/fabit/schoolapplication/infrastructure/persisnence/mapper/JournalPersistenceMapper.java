package com.fabit.schoolapplication.infrastructure.persisnence.mapper;

import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementOfStudentDto;
import com.fabit.schoolapplication.domain.journalofstudent.agregate.JournalOfStudent;
import com.fabit.schoolapplication.domain.journalofstudent.entity.JournalByDiscipline;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalByDisciplineId;
import com.fabit.schoolapplication.domain.journalofstudent.id.JournalOfStudentId;
import com.fabit.schoolapplication.domain.journalofstudent.valueobject.Achievement;
import com.fabit.schoolapplication.domain.student.StudentId;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.AchievementEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalByDisciplineEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.entity.journalofstudent.JournalOfStudentEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JournalPersistenceMapper {
  private final StudentRepository studentRepository;


  public JournalOfStudentEntity mapDomainJournalOfStudentToEntity(
    JournalOfStudent journalOfStudent
  ) {
    JournalOfStudentEntity journalOfStudentEntity = new JournalOfStudentEntity();
    journalOfStudentEntity.setId(
      journalOfStudent.getId().getLongValue()
    );
    journalOfStudentEntity.setStudent(
      studentRepository.findById(journalOfStudent.getStudentId().getValue()).get()
    );
    journalOfStudentEntity.setJournalByDisciplineEntityList(
      mapDomainJournalByDisciplineListToEntity(
        journalOfStudent.getJournalByDisciplineList()
      )
    );
    return journalOfStudentEntity;
  }

  public List<JournalByDisciplineEntity> mapDomainJournalByDisciplineListToEntity(
    List<JournalByDiscipline> journalByDisciplineList
  ) {
    List<JournalByDisciplineEntity> journalByDisciplineEntityList = new ArrayList<>();
    journalByDisciplineList
      .forEach(journalByDiscipline -> journalByDisciplineEntityList.add(
        mapDomainJournalByDisciplineToEntity(journalByDiscipline)
      ));
    return journalByDisciplineEntityList;
  }

  public JournalByDisciplineEntity mapDomainJournalByDisciplineToEntity(
    JournalByDiscipline journalByDiscipline
  ) {
    JournalByDisciplineEntity journalByDisciplineEntity = new JournalByDisciplineEntity();
    journalByDisciplineEntity.setDiscipline(journalByDiscipline.getDiscipline());
    journalByDisciplineEntity.setId(journalByDiscipline.getId().getLongValue());
    journalByDisciplineEntity.setAchievements(
      mapDomainAchievementToEntity(journalByDiscipline.getAchievements())
    );
    return journalByDisciplineEntity;
  }

  public List<AchievementEntity> mapDomainAchievementToEntity(
    List<Achievement> achievements
  ) {
    List<AchievementEntity> achievementEntityList = new ArrayList<>();
    for (Achievement achievement : achievements) {
      AchievementEntity achievementEntity = new AchievementEntity();
      achievementEntity.setValue(achievement.getValue());
      achievementEntity.setDateOfLesson(achievement.getDateOfLesson());
      achievementEntityList.add(achievementEntity);
    }
    return achievementEntityList;
  }


  public JournalOfStudent mapEntityJournalOfStudentToDomain(
    JournalOfStudentEntity journalOfStudentEntity
  ) {
    return JournalOfStudent.copyOf(
      JournalOfStudentId.of(
        journalOfStudentEntity.getId()
      ),
      StudentId.of(
        journalOfStudentEntity.getStudent().getId()
      ),
      mapEntityJournalByDisciplineListToDomain(
        journalOfStudentEntity.getJournalByDisciplineEntityList()
      )
    );
  }

  public List<JournalByDiscipline> mapEntityJournalByDisciplineListToDomain(
    List<JournalByDisciplineEntity> journalByDisciplineEntities) {
    List<JournalByDiscipline> journalsByDiscipline = new ArrayList<>();
    journalByDisciplineEntities
      .forEach(
        journalByDisciplineEntity -> journalsByDiscipline.add(
          mapEntityJournalByDisciplineToDomain(journalByDisciplineEntity)
        ));
    return journalsByDiscipline;
  }

  public JournalByDiscipline mapEntityJournalByDisciplineToDomain(
    JournalByDisciplineEntity journalByDisciplineEntity
  ) {
    return JournalByDiscipline
      .copyOf(
        JournalByDisciplineId.of(journalByDisciplineEntity.getId()),
        journalByDisciplineEntity.getDiscipline(),
        mapAchievementEntityListToDomain(journalByDisciplineEntity.getAchievements())
      );
  }

  public List<Achievement> mapAchievementEntityListToDomain(
    List<AchievementEntity> achievementEntities
  ) {
    List<Achievement> achievements = new ArrayList<>();
    achievementEntities
      .forEach(achievementEntity -> achievements.add(
        mapAchievementEntityToDomain(achievementEntity)
      ));
    return achievements;
  }

  public Achievement mapAchievementEntityToDomain(
    AchievementEntity achievementEntity
  ) {
    return new Achievement(
      achievementEntity.getDateOfLesson(),
      achievementEntity.getValue()
    );
  }

  public List<AchievementOfStudentDto> mapAchievementsEntityListToDtoList(
    List<AchievementEntity> achievementEntities
  ) {
    List<AchievementOfStudentDto> achievementForStudentDtoList = new ArrayList<>();
    for (AchievementEntity achievementEntity : achievementEntities) {
      AchievementOfStudentDto achievement = new AchievementOfStudentDto();
      achievement.setAchievement(achievementEntity.getValue());
      achievement.setDateOfLesson(achievementEntity.getDateOfLesson());
      achievementForStudentDtoList.add(achievement);
    }
    return achievementForStudentDtoList;
  }
}
