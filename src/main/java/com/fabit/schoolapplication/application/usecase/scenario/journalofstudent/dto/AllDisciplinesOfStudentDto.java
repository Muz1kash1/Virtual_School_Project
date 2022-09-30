package com.fabit.schoolapplication.application.usecase.scenario.journalofstudent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllDisciplinesOfStudentDto {
  private List<String> disciplinesOfStudent;
}
