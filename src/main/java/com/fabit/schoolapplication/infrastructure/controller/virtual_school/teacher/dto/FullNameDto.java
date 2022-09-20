package com.fabit.schoolapplication.infrastructure.controller.virtual_school.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullNameDto {
  String name;
  String surname;
  String patronymic;
}