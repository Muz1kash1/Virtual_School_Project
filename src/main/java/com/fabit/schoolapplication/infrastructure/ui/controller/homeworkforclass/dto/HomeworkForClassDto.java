package com.fabit.schoolapplication.infrastructure.ui.controller.homeworkforclass.dto;

import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
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
