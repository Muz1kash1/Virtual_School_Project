package com.fabit.schoolapplication.infrastructure.ui.controller.academicachievementofstudent.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllAchievementsOfStudentByDisciplineResponseDto {

  List<AchievementOfStudentResponseDto> achievements;
}
