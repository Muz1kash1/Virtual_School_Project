package com.fabit.schoolapplication.domain.student;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class Student {
  private long id;
  private String name;
  private Snils snils;
  private BirthCertificate birthCertificate;
  private Passport passport;
  private LocalDate birthday;

  private Student(long id, String name, Snils snils, BirthCertificate birthCertificate,
                  LocalDate birthday) {
    this.id = id;
    this.name = name;
    this.snils = snils;
    this.birthCertificate = birthCertificate;
    this.birthday = birthday;
  }

  private Student(long id, String name, Snils snils, Passport passport, LocalDate birthday) {
    this.id = id;
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
   * @param id               id ученика
   * @param name             имя
   * @param snils            СНИЛС
   * @param birthCertificate свидетельство о рождении
   * @param birthday         день рожденияя
   * @return the student
   */
  public static Student create(long id, String name, Snils snils, BirthCertificate birthCertificate,
                               LocalDate birthday) {
    return new Student(id, name, snils, birthCertificate, birthday);
  }

  /**
   * Создание ученика возрастом минимум 14 лет.
   *
   * @param id       id
   * @param name     имя
   * @param snils    СНИЛС
   * @param passport паспорт
   * @param birthday день рождения
   * @return студент
   */
  public static Student create(long id, String name, Snils snils, Passport passport,
                               LocalDate birthday) {
    return new Student(id, name, snils, passport, birthday);
  }

  /**
   * замена СНИЛСа.
   *
   * @param snils СНИЛС
   */
  public void changeSnils(Snils snils) {
    this.snils = snils;
  }

  /**
   * Замена свидетельства о рождении.
   *
   * @param birthCertificate день рождения
   */
  public void changeBirthCertificate(BirthCertificate birthCertificate) {
    this.birthCertificate = birthCertificate;
  }

  /**
   * Добавление паспорта.
   *
   * @param passport паспорт
   */
  public void addPassport(Passport passport) {
    this.passport = passport;
  }
}