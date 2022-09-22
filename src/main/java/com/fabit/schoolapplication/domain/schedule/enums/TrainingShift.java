package com.fabit.schoolapplication.domain.schedule.enums;

import lombok.Getter;

/**
 * Перечисление описывающее смену занятий уроков (I|II).
 */
@Getter
public enum TrainingShift {

  FIRST_SHIFT_OF_CLASSES("I"),
  SECOND_SHIFT_OF_CLASSES("II");

  private final String value;

  TrainingShift(String value) {
    this.value = value;
  }

}
