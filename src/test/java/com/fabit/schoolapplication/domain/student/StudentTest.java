package com.fabit.schoolapplication.domain.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTest {

  @Test
  @DisplayName("Создается объект ученика младше 14 лет со свидетельством о рождении")
  void createStudent12Years() {
    Student student = Student.create(1, "Петя Губкин", new Snils("123-343-2231-32"),
        BirthCertificate.create("МК", "55555"), LocalDate.of(2010, 9, 15));
    assertEquals(1l, student.getId());
    assertEquals("Петя Губкин", student.getName());
    assertEquals("123-343-2231-32", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Создается объект ученика старше 15 лет с паспортом")
  void createStudent15Years() {
    Student student =
        Student.create(1, "Петя Губкин", new Snils("123-343-2231-32"), new Passport("МК", "88888"),
            LocalDate.of(2007, 9, 15));
    assertEquals(1l, student.getId());
    assertEquals("Петя Губкин", student.getName());
    assertEquals("123-343-2231-32", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Создается объект ученика младше 14 лет c паспортом")
  void createStudent12YearsWithPassportShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> Student.create(1, "Петя Губкин", new Snils("123-343-2231-32"),
            new Passport("МК", "89292"), LocalDate.of(2010, 9, 15)));
  }

  @Test
  @DisplayName("Изменяем снилс")
  void changeSnils() {
    Student student =
        Student.create(1, "Петя Губкин", new Snils("123-343-2231-32"), new Passport("МК", "89292"),
            LocalDate.of(2007, 9, 15));
    student.changeSnils(new Snils("777-777-7777-77"));
    assertEquals("777-777-7777-77", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Изменяем сведетельство о рождении")
  void changeBirthCertificate() {
    Student student = Student.create(1, "Петя Губкин", new Snils("123-343-2231-32"),
        BirthCertificate.create("МК", "89292"), LocalDate.of(2007, 9, 15));
    student.changeBirthCertificate(BirthCertificate.create("MK", "99999"));
    assertEquals("99999", student.getBirthCertificate().getNumber());
    assertEquals("MK", student.getBirthCertificate().getSerial());
  }

  @Test
  @DisplayName("Добавляем пасспорт")
  void addPassport() {
    Student student =
        Student.create(1, "Петя Губкин", new Snils("123-343-2231-32"), new Passport("MK", "11111"),
            LocalDate.of(2007, 9, 15));
    student.addPassport(new Passport("MK", "99999"));
    assertEquals("99999", student.getPassport().getNumber());
    assertEquals("MK", student.getPassport().getSerial());
  }
}