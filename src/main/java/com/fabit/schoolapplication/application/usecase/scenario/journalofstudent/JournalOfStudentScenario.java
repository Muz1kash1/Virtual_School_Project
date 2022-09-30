package com.fabit.schoolapplication.application.usecase.scenario.journalofstudent;

import com.fabit.schoolapplication.application.usecase.access.journalofstudent.JournalOfStudentService;
import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllDisciplinesOfStudentDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JournalOfStudentScenario {
  private final JournalOfStudentService journalOfStudentService;

  public void add(long studentId) {
    journalOfStudentService.save(studentId);
  }

  public void remove(long studentId) {
    journalOfStudentService.delete(studentId);
  }

  public AllDisciplinesOfStudentDto getAllDisciplinesForStudent(long studentId) {
    return journalOfStudentService.getAllByStudent(studentId);
  }
}
