package com.fabit.schoolapplication.application.usecase.scenario.journalofstudent;

import com.fabit.schoolapplication.application.usecase.access.journalofstudent.JournalByDisciplineService;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementForStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AchievementOfStudentDto;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllAchievementsOfStudentByDisciplineDto;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@RequiredArgsConstructor
public class JournalByDisciplineScenario {
  private final JournalByDisciplineService journalByDisciplineService;

  public void add(long studentId, String discipline) {
    journalByDisciplineService.save(studentId, discipline);
  }

  public void remove(long studentId, String discipline) {
    journalByDisciplineService.delete(studentId, discipline);
  }

  public AllAchievementsOfStudentByDisciplineDto getAllAchievementsByDiscipline(
    long studentId, String discipline
  ) {
    return journalByDisciplineService.getAllByStudentAndDiscipline(
      studentId, discipline
    );
  }

  public AchievementOfStudentDto getAchievementByDisciplineAndDateOfLesson(
    long studentId, String discipline, LocalDate dateOfLesson
  ) {
    return journalByDisciplineService.getByDisciplineAndDateOfLesson(
      studentId, discipline, dateOfLesson
    );
  }

  public void addAchievement(
    long studentId, AchievementForStudentDto achievement
  ) {
    journalByDisciplineService.addAchievement(studentId, achievement);
  }

  public void changeAchievement(
    long studentId, AchievementForStudentDto achievement
  ) {
    journalByDisciplineService.changeAchievement(studentId, achievement);
  }

  public void removeAchievement(
    long studentId, AchievementForStudentDto achievement
  ) {
    journalByDisciplineService.deleteAchievement(studentId, achievement);
  }
}
