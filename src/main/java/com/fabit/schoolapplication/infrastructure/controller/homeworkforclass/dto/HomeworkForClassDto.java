package com.fabit.schoolapplication.infrastructure.controller.homeworkforclass.dto;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkForClassDto {
  @JsonProperty("id")
  private Long id;
  @JsonProperty("discipline")
  private Discipline discipline;
  @JsonProperty("task")
  private String task;
  @JsonProperty("schoolClassId")
  private Long schoolClassId;
  @JsonProperty("date")
  private LocalDate date;
}
