package com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddJournalByDisciplineForStudentRequestDto {
  private long studentId;
  private String discipline;
}
