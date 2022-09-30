package com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchievementOfStudentDto {
  private LocalDate dateOfLesson;
  private String achievement;
}
