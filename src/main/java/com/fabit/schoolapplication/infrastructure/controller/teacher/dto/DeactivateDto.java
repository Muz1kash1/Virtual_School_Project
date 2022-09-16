package com.fabit.schoolapplication.infrastructure.controller.teacher.dto;

import lombok.Data;

@Data
public class DeactivateDto {
  private long teacherId;
  private String from;
  private String to;
}
