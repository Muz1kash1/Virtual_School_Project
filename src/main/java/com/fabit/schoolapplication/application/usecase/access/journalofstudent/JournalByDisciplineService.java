package com.fabit.schoolapplication.application.usecase.access.journalofstudent;

import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementForStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementOfStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllAchievementsOfStudentByDisciplineDto;
import java.time.LocalDate;

public interface JournalByDisciplineService {
  void save(long studentId, String discipline);

  void delete(long studentId, String discipline);

  AllAchievementsOfStudentByDisciplineDto getAllByStudentAndDiscipline(
    long studentId, String discipline
  );

  AchievementOfStudentDto getByDisciplineAndDateOfLesson(
    long studentId, String discipline, LocalDate dateOfLesson
  );

  void addAchievement(
    long studentId, AchievementForStudentDto achievement
  );

  void changeAchievement(
    long studentId, AchievementForStudentDto achievement
  );

  void deleteAchievement(
    long studentId, AchievementForStudentDto achievement
  );
}
