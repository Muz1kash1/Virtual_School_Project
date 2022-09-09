package com.fabit.schoolapplication.domain.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeacherTest {

  @Test
  @DisplayName("Создается валидный объект учителя")
  void createTeacher_ValidData_shouldReturnValidObjectWithID1() {
    Teacher teacher =
        Teacher.create(
            1L,
            new FullName("Иван", "Иванов", "Иванович"),
            new Passport("1999", "89292"),
            new Snils("123-343-2231-32"),
            10);
    assertEquals(1L, teacher.getId());
  }

  @Test
  @DisplayName("При создании объекта валидируется стаж работы")
  void createTeacher_NonValidStandingYears_shouldThrowIllegalArgumentException() {
    Throwable thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Teacher teacher =
                  Teacher.create(
                      1L,
                      new FullName("Иван", "Иванов", "Иванович"),
                      new Passport("1999", "89292"),
                      new Snils("123-343-2231-32"),
                      71);
            });
  }

  @Test
  @DisplayName("Нельзя изменить стаж работы на стаж больше 70 лет")
  void changeStandingYears_NonValidValue100_shouldThrowIllegalArgumentException() {
    Teacher teacher =
        Teacher.create(
            1L,
            new FullName("Иван", "Иванов", "Иванович"),
            new Passport("1999", "89292"),
            new Snils("123-343-2231-32"),
            10);
    Throwable thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              teacher.changeStandingYears(100);
            });
  }

  @Test
  @DisplayName("Нельзя изменить стаж работы на стаж меньше чем был у учителя")
  void changeStandingYears_NonValidValue9_shouldThrowIllegalArgumentException() {
    Teacher teacher =
        Teacher.create(
            1L,
            new FullName("Иван", "Иванов", "Иванович"),
            new Passport("1999", "89292"),
            new Snils("123-343-2231-32"),
            10);
    Throwable thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              teacher.changeStandingYears(9);
            });
  }

  @Test
  @DisplayName("Учитель может изменить стаж работы")
  void changeStandingYears_ValidData_shouldPass() {
    Teacher teacher =
        Teacher.create(
            1L,
            new FullName("Иван", "Иванов", "Иванович"),
            new Passport("1999", "89292"),
            new Snils("123-343-2231-32"),
            10);
    teacher.changeStandingYears(11);
    assertEquals(11, teacher.getStandingYears());
  }
}
