package com.fabit.schoolapplication.domain.schedule.enums;

import lombok.Getter;

/**
 * Перечисление описывающее учебные дни недели
 */
@Getter
public enum DayOfWeek {

  MONDAY("пн"),
  TUESDAY("вт"),
  WEDNESDAY("ср"),
  THURSDAY("чт"),
  FRIDAY("пт"),
  SATURDAY("сб");

  private final String value;

  DayOfWeek(String value) {
    this.value = value;
  }

}
