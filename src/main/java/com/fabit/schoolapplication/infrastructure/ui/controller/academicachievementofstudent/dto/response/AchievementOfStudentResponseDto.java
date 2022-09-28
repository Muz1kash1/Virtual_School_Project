package com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchievementOfStudentResponseDto {
  private String dateOfLesson;
  private String achievement;
}
