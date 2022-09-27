package com.fabit.schoolapplication.domain.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fabit.schoolapplication.domain.generalvalueobject.fullname.FullName;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.UkrainePassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTest {
  private final Clock clock = Clock.fixed(Instant.parse("2022-09-15T00:00:00Z"), ZoneOffset.UTC);

  @Test
  @DisplayName("Создается объект ученика младше 14 лет со свидетельством о рождении")
  void createStudent12Years() {

    Student student = Student.of(
      StudentId.of(1),
      FullName.of("Петя", "Губкин", "Васильевич"),
      Snils.of("123-343-223-32"),
      BirthCertificate.of("2222", "999999",
        LocalDate.of(2010, 9, 15), clock
      )
    );

    assertEquals(1L, student.getId().getValue());
    assertEquals("Васильевич", student.getFullName().getPatronymic());
    assertEquals("123-343-223-32", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Создается объект ученика старше 15 лет с паспортом")
  void createStudent15Years() {

    Student student = Student.of(
      StudentId.of(1),
      FullName.of("Петя", "Губкин", "Васильевич"),
      Snils.of("123-343-223-32"),
      BirthCertificate.of("2222", "999999",
        LocalDate.of(2007, 9, 15), clock
      ),
      RussianPassport.of("1111", "888888",
        LocalDate.of(2007, 9, 15), clock
      )
    );

    assertEquals(1L, student.getId().getValue());
    assertEquals("Васильевич", student.getFullName().getPatronymic());
    assertEquals("123-343-223-32", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Создается объект ученика младше 14 лет c паспортом должен выкинуть ошибку")
  void createStudent12YearsWithPassportShouldThrowIllegalArgumentException() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Student.of(
        StudentId.of(1),
        FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-223-32"),
        BirthCertificate.of("2222", "999999",
          LocalDate.of(2010, 9, 15), clock
        ),
        RussianPassport.of(
          "1111", "888888",
          LocalDate.of(2010, 9, 15), clock
        )
      )
    );
  }

  @Test
  @DisplayName("Создается объект ученика c невалидным паспортом должен выкинуть ошибку")
  void createStudentWithInvalidPassportShouldThrowIllegalArgumentException() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Student.of(
        StudentId.of(1),
        FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-223-32"),
        BirthCertificate.of("2222", "999999",
          LocalDate.of(2010, 9, 15), clock
        ),
        RussianPassport.of(
          "99", "89292",
          LocalDate.of(2010, 9, 15), clock
        )
      )
    );
  }

  @Test
  @DisplayName("Создается объект ученика c невалидным свидетельством о рождении должен выкинуть ошибку")
  void createStudentWithInvalidBirthCertificateShouldThrowIllegalArgumentException() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Student.of(
        StudentId.of(1),
        FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-223-32"),
        BirthCertificate.of(
          "99", "89292",
          LocalDate.of(2010, 9, 15), clock
        )
      )
    );
  }

  @Test
  @DisplayName("Создается объект ученика c невалидным СНИЛС  должен выкинуть ошибку")
  void createStudentWithInvalidSnilsShouldThrowIllegalArgumentException() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Student.of(
        StudentId.of(1),
        FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-2233-32"),
        BirthCertificate.of(
          "99", "89292",
          LocalDate.of(2010, 9, 15), clock
        )
      )
    );
  }

  @Test
  @DisplayName("Создается объект ученика младше 5 лет должен выкинуть ошибку")
  void createStudent4YearsShouldThrowIllegalArgumentException() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Student.of(
        StudentId.of(1),
        FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-223-32"),
        BirthCertificate.of(
          "2222", "999999",
          LocalDate.of(2018, 9, 15), clock
        )
      )
    );
  }

  @Test
  @DisplayName("Создается объект ученика с разной датой рождения в паспорте и св. о рожд. должен выкинуть ошибку")
  void createStudentWithNotValidDataShouldThrowIllegalArgumentException() {
    assertThrows(
      IllegalArgumentException.class,
      () -> Student.of(
        StudentId.of(1),
        FullName.of("Петя", "Губкин", "Васильевич"),
        Snils.of("123-343-223-32"),
        BirthCertificate.of("2222", "999999",
          LocalDate.of(2010, 9, 15), clock
        ),
        RussianPassport.of("1111", "888888",
          LocalDate.of(2007, 9, 15), clock
        )
      )
    );
  }

  @Test
  @DisplayName("Изменяем снилс у ученика")
  void changeSnils() {

    Student student = Student.of(
      StudentId.of(1),
      FullName.of("Петя", "Губкин", "Васильевич"),
      Snils.of("123-343-223-32"),
      BirthCertificate.of("2222", "999999",
        LocalDate.of(2007, 9, 15), clock
      ),
      UkrainePassport.of(
        "1111", "888888",
        LocalDate.of(2007, 9, 15), clock
      )
    );

    student.changeSnils(Snils.of("777-777-777-77"));

    assertEquals("777-777-777-77", student.getSnils().getNumberView());
  }

  @Test
  @DisplayName("Изменяем сведетельство о рождении у ученика")
  void changeBirthCertificate() {

    Student student = Student.of(
      StudentId.of(1),
      FullName.of("Петя", "Губкин", "Васильевич"),
      Snils.of("123-343-223-32"),
      BirthCertificate.of(
        "2223", "788778",
        LocalDate.of(2007, 9, 15), clock
      )
    );

    student.changeBirthCertificate(
      BirthCertificate.of("2222", "999999",
        LocalDate.of(2007, 9, 15), clock
      )
    );

    assertEquals("999999", student.getBirthCertificate().getNumber());
    assertEquals("2222", student.getBirthCertificate().getSerial());
  }

  @Test
  @DisplayName("Добавляем пасспорт ученику старе 14 лет")
  void addPassport() {

    Student student = Student.of(
      StudentId.of(1),
      FullName.of("Петя", "Губкин", "Васильевич"),
      Snils.of("123-343-223-32"),
      BirthCertificate.of("2222", "999999",
        LocalDate.of(2007, 9, 15), clock
      ),
      RussianPassport.of(
        "2222", "999999",
        LocalDate.of(2007, 9, 15), clock
      )
    );

    student.addPassport(
      RussianPassport.of("1111", "555555",
        LocalDate.of(2007, 9, 15), clock
      )
    );

    assertEquals("555555", student.getPassport().getNumber());
    assertEquals("1111", student.getPassport().getSerial());
  }
}