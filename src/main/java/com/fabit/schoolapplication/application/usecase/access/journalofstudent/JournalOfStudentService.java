package com.fabit.schoolapplication.application.usecase.access.journalofstudent;

import com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto.AllDisciplinesOfStudentDto;

public interface JournalOfStudentService {
  void save(long studentId);

  void delete(long studentId);

  AllDisciplinesOfStudentDto getAllByStudent(long studentId);
}
