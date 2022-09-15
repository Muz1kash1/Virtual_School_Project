package com.fabit.schoolapplication.infrastructure.controller.teacher.dto;

import lombok.Data;

@Data
public class TeacherDto {
  private int standingYears;
  private FullNameDto fullName;
  private PassportDto passport;
  private SnilsDto snils;
  private boolean isActive;
}
