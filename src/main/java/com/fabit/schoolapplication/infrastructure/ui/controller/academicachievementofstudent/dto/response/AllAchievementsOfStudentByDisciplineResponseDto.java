package com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllAchievementsOfStudentByDisciplineResponseDto {
  List<AchievementOfStudentResponseDto> achievements;
}
