package com.fabit.schoolapplication.domain.student;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.StudentId;
import java.time.LocalDate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Student {
  private StudentId studentId;
  private String name;
  private Snils snils;
  private BirthCertificate birthCertificate;
  private Passport passport;
  private LocalDate birthday;

  private Student(StudentId studentId, String name, Snils snils, BirthCertificate birthCertificate,
                  LocalDate birthday) {
    this.studentId = studentId;
    this.name = name;
    this.snils = snils;
    this.birthCertificate = birthCertificate;
    this.birthday = birthday;
  }

  private Student(StudentId studentId, String name, Snils snils, Passport passport,
                  LocalDate birthday) {
    this.studentId = studentId;
    this.name = name;
    this.snils = snils;
    if (LocalDate.now().getYear() - birthday.getYear() >= 14) {
      this.passport = passport;
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Создание ученика младше 14 лет.
   *
   * @param studentId        id ученика
   * @param name             имя
   * @param snils            СНИЛС
   * @param birthCertificate свидетельство о рождении
   * @param birthday         день рожденияя ученика
   * @return the student
   */
  public static Student of(StudentId studentId, String name, Snils snils,
                           BirthCertificate birthCertificate, LocalDate birthday) {
    return new Student(studentId, name, snils, birthCertificate, birthday);
  }

  /**
   * Создание ученика возрастом минимум 14 лет.
   *
   * @param studentId id студента
   * @param name      имя
   * @param snils     СНИЛС
   * @param passport  паспорт
   * @param birthday  день рождения
   * @return студент
   */
  public static Student of(StudentId studentId, String name, Snils snils, Passport passport,
                           LocalDate birthday) {
    return new Student(studentId, name, snils, passport, birthday);
  }

  /**
   * замена СНИЛСа.
   *
   * @param snils СНИЛС
   */
  public void changeSnils(Snils snils) {
    this.snils = snils;
    log.info("СНИЛС изменен");
  }

  /**
   * Замена свидетельства о рождении.
   *
   * @param birthCertificate день рождения
   */
  public void changeBirthCertificate(BirthCertificate birthCertificate) {
    this.birthCertificate = birthCertificate;
    log.info("Свидетельство о рождении успешно изменено");
  }

  /**
   * Добавление паспорта.
   *
   * @param passport паспорт
   */
  public void addPassport(Passport passport) {
    this.passport = passport;
    log.info("Паспорт успешно добавлен");
  }
}