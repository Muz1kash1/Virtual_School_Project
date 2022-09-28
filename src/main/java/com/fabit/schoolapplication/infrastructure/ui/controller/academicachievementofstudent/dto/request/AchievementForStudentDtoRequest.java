package com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchievementForStudentDtoRequest {
  private long studentId;
  private String discipline;
  private String achievement;
  private String dateOfLesson;
}
