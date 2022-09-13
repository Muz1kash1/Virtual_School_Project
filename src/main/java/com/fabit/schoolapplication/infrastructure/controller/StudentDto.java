package com.fabit.schoolapplication.infrastructure.controller;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class StudentDto implements Serializable {
  private String name;
  private String snils;
  private String birthCertificate;
  private String passport;
  private LocalDate birthday;
}