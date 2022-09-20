package com.fabit.schoolapplication.infrastructure.controller.virtual_school.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
  private int standingYears;
  private FullNameDto fullName;
  private PassportDto passport;
  private SnilsDto snils;
  private boolean isActive;
}
