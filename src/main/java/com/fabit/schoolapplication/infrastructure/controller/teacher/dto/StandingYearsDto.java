package com.fabit.schoolapplication.infrastructure.controller.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandingYearsDto {
  private long teacherId;
  private int standingYears;
}
