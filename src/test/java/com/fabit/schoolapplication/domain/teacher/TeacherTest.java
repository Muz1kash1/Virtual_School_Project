package com.fabit.schoolapplication.domain.teacher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fabit.schoolapplication.domain.generalvalueobject.fullname.FullName;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeacherTest {
  private final Clock clock = Clock.fixed(Instant.parse("2022-09-15T00:00:00Z"), ZoneOffset.UTC);

  @Test
  @DisplayName("Учителю можно поменять статус на НЕАКТИВНЫЙ с одной даты по другую")
  void deactivate_valid_shouldPass() {

    Teacher teacher =
      Teacher.of(
        TeacherId.of(1L),
        FullName.of("Иванов", "Иван", "Иванович"),
        RussianPassport.of("1111", "222222", LocalDate.of(1980, 9, 15), clock),
        Snils.of("132-241-324-21"));

    teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 20));

    assertFalse(teacher.isActive());
  }

  @Test
  @DisplayName("Учителю нельзя поменять статус на НЕАКТИВНЫЙ если он уже неактивный")
  void deactivate_invalidState_shouldThrowIllegalStateException() {

    Teacher teacher =
      Teacher.of(
        TeacherId.of(1L),
        FullName.of("Иванов", "Иван", "Иванович"),
        RussianPassport.of("1111", "222222", LocalDate.of(1980, 9, 15), clock),
        Snils.of("132-241-324-21"));

    teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 20));

    assertThrows(
      IllegalStateException.class,
      () -> teacher.deactivate(LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 30)));
  }

  @Test
  @DisplayName("Учителю нельзя поменять статус на НЕАКТИВНЫЙ если передать некорректную дату")
  void deactivate_invalidDate_shouldThrowIllegalStateException() {

    Teacher teacher =
      Teacher.of(
        TeacherId.of(1L),
        FullName.of("Иванов", "Иван", "Иванович"),
        RussianPassport.of("1111", "222222", LocalDate.of(1980, 9, 15), clock),
        Snils.of("132-241-324-21"));

    assertThrows(
      IllegalStateException.class,
      () -> teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 9, 20)));
  }

  @Test
  @DisplayName("Можно изменить статус учителя на АКТИВНЫЙ")
  void activate_valid_shouldPass() {
    Teacher teacher =
      Teacher.of(
        TeacherId.of(1L),
        FullName.of("Иванов", "Иван", "Иванович"),
        RussianPassport.of("1111", "222222", LocalDate.of(1980, 9, 15), clock),
        Snils.of("132-241-324-21"));

    teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 20));

    teacher.activate();

    assertTrue(teacher.isActive());
  }

  @Test
  @DisplayName("Учителю нельзя поменять статус на АКТИВНЫЙ если он уже АКТИВНЫЙ")
  void activate_invalidState_shouldThrowIllegalStateException() {

    Teacher teacher =
      Teacher.of(
        TeacherId.of(1L),
        FullName.of("Иванов", "Иван", "Иванович"),
        RussianPassport.of("1111", "222222", LocalDate.of(1980, 9, 15), clock),
        Snils.of("132-241-324-21"));

    assertThrows(IllegalStateException.class, teacher::activate);
  }
}
