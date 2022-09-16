package com.fabit.schoolapplication.domain.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTest {

  @Test
  @DisplayName("Создается объект ученика младше 14 лет со свидетельством о рождении")
  void createStudent12Years() {
    Student student = Student.of(StudentId.of(1), FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-2231-32"), BirthCertificate.of("МК", "55555"), "15.09.2010");
    assertEquals(1l, student.getStudentId().getValue());
    assertEquals("Васильевич", student.getFullName().getPatronymic());
    assertEquals("123-343-2231-32", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Создается объект ученика старше 15 лет с паспортом")
  void createStudent15Years() {
    Student student = Student.of(StudentId.of(1), FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-2231-32"), Passport.of("МК", "88888"), "15.09.2007");
    assertEquals(1l, student.getStudentId().getValue());
    assertEquals("Васильевич", student.getFullName().getPatronymic());
    assertEquals("123-343-2231-32", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Создается объект ученика младше 14 лет c паспортом должен выкинуть ошибку")
  void createStudent12YearsWithPassportShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> Student.of(StudentId.of(1), FullName.of("Петя", "Губкин", "Васильевич"),
            Snils.of("123-343-2231-32"), Passport.of("МК", "89292"),
            "15.09.2010"));
  }

  @Test
  @DisplayName("Изменяем снилс у ученика")
  void changeSnils() {
    Student student = Student.of(StudentId.of(1), FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-2231-32"), Passport.of("МК", "88888"), "15.09.2007");
    student.changeSnils(Snils.of("777-777-7777-77"));
    assertEquals("777-777-7777-77", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Изменяем сведетельство о рождении у ученика")
  void changeBirthCertificate() {
    Student student = Student.of(StudentId.of(1), FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-2231-32"), BirthCertificate.of("МК", "89292"),
        "15.09.2007");
    student.changeBirthCertificate(BirthCertificate.of("MK", "99999"));
    assertEquals("99999", student.getBirthCertificate().getNumber());
    assertEquals("MK", student.getBirthCertificate().getSerial());
  }

  @Test
  @DisplayName("Добавляем пасспорт ученику старе 14 лет")
  void addPassport() {
    Student student = Student.of(StudentId.of(1), FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-2231-32"), Passport.of("MK", "11111"), "15.09.2007");
    student.addPassport(Passport.of("MK", "99999"));
    assertEquals("99999", student.getPassport().getNumber());
    assertEquals("MK", student.getPassport().getSerial());
  }
}