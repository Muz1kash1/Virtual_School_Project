package com.fabit.schoolapplication.infrastructure.ui.controller.student.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto implements Serializable {
  private Long id;
  private FullNameDto name;
  private SnilsDto snils;
  private BirthCertificateDto birthCertificate;
  private PassportDto passport;
}