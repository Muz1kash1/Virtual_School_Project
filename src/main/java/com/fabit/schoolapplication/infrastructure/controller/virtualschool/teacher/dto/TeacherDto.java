package com.fabit.schoolapplication.infrastructure.controller.virtualschool.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
  private FullNameDto fullName;
  private PassportDto passport;
  private SnilsDto snils;
}
