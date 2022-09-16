package com.fabit.schoolapplication.domain.teacher;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeacherTest {

  @Test
  @DisplayName("Учителю можно поменять статус на НЕАКТИВНЫЙ с одной даты по другую")
  void deactivate_valid_shouldPass() {
    Teacher teacher =
        Teacher.of(
            TeacherId.of(1L),
            FullName.of("Иванов", "Иван", "Иванович"),
            Passport.of("1111", "222222"),
            Snils.of("132-241-324-21"),
            10,
            true);
    teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 20));
    assertEquals(false, teacher.isActive());
  }

  @Test
  @DisplayName("Учителю нельзя поменять статус на НЕАКТИВНЫЙ если он уже неактивный")
  void deactivate_invalidState_shouldThrowIllegalStateException() {
    Teacher teacher =
        Teacher.of(
            TeacherId.of(1L),
            FullName.of("Иванов", "Иван", "Иванович"),
            Passport.of("1111", "222222"),
            Snils.of("132-241-324-21"),
            10,
            true);
    teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 20));
    assertThrows(
        IllegalStateException.class,
        () -> {
          teacher.deactivate(LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 30));
        });
  }

  @Test
  @DisplayName("Учителю нельзя поменять статус на НЕАКТИВНЫЙ если передать некорректную дату")
  void deactivate_invalidDate_shouldThrowIllegalStateException() {
    Teacher teacher =
        Teacher.of(
            TeacherId.of(1L),
            FullName.of("Иванов", "Иван", "Иванович"),
            Passport.of("1111", "222222"),
            Snils.of("132-241-324-21"),
            10,
            true);
    assertThrows(
        IllegalStateException.class,
        () -> {
          teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 9, 20));
        });
  }

  @Test
  @DisplayName("Можно изменить статус учителя на АКТИВНЫЙ")
  void activate_valid_shouldPass() {
    Teacher teacher =
        Teacher.of(
            TeacherId.of(1L),
            FullName.of("Иванов", "Иван", "Иванович"),
            Passport.of("1111", "222222"),
            Snils.of("132-241-324-21"),
            10,
            true);
    teacher.deactivate(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 20));
    teacher.activate();
    assertEquals(true, teacher.isActive());
  }

  @Test
  @DisplayName("Учителю нельзя поменять статус на АКТИВНЫЙ если он уже АКТИВНЫЙ")
  void activate_invalidState_shouldThrowIllegalStateException() {
    Teacher teacher =
        Teacher.of(
            TeacherId.of(1L),
            FullName.of("Иванов", "Иван", "Иванович"),
            Passport.of("1111", "222222"),
            Snils.of("132-241-324-21"),
            10,
            true);
    assertThrows(
        IllegalStateException.class,
        () -> {
          teacher.activate();
        });
  }
}
