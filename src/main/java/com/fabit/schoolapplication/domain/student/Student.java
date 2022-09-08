package com.fabit.schoolapplication.domain.student;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import java.time.LocalDate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
   * @param id               the id
   * @param name             the name
   * @param snils            the snils
   * @param birthCertificate the birth certificate
   * @param birthday         the birthday
   * @return the student
   */
  public static Student create(long id, String name, Snils snils, BirthCertificate birthCertificate,
                               LocalDate birthday) {
    return new Student(id, name, snils, birthCertificate, birthday);
  }

  /**
   * Создание ученика возрастом минимум 14 лет.
   *
   * @param id       the id
   * @param name     the name
   * @param snils    the snils
   * @param passport the passport
   * @param birthday the birthday
   * @return the student
   */
  public static Student create(long id, String name, Snils snils, Passport passport,
                               LocalDate birthday) {
    return new Student(id, name, snils, passport, birthday);
  }

  /**
   * замена СНИЛСа.
   *
   * @param snils the snils
   */
  public void changeSnils(Snils snils) {
    this.snils = snils;
  }

  /**
   * Замена свидетельства о рождении.
   *
   * @param birthCertificate the birth certificate
   */
  public void changeBirthCertificate(BirthCertificate birthCertificate) {
    this.birthCertificate = birthCertificate;
  }

  /**
   * Добавление паспорта.
   *
   * @param passport the passport
   */
  public void addPassport(Passport passport) {
    this.passport = passport;
  }
}