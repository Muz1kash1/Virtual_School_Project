package com.fabit.schoolapplication.domain.student;

import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.domain.student.event.StudentCreatedEvent;
import com.fabit.schoolapplication.domain.student.event.StudentEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Getter
@Slf4j
@NoArgsConstructor
public class Student {
  private StudentId studentId;
  private String name;
  private Snils snils;
  private BirthCertificate birthCertificate;
  private Passport passport;
  private LocalDate birthday;
  public static final List<StudentEvent> domainEvents = new ArrayList<>();

  protected void registerEvent(StudentEvent event) {
    Assert.notNull(event, "Domain event must not be null");
    this.domainEvents.add(event);
  }

  private Student(StudentId studentId, String name, Snils snils, BirthCertificate birthCertificate,
                  LocalDate birthday) {
    this.studentId = studentId;
    this.name = name;
    this.snils = snils;
    this.birthCertificate = birthCertificate;
    this.birthday = birthday;
    registerEvent(new StudentCreatedEvent(this));
  }

  private Student(StudentId studentId, String name, Snils snils, Passport passport,
                  LocalDate birthday) {
    this.studentId = studentId;
    this.name = name;
    this.snils = snils;
    if (passport.isValidAge(birthday)) {
      this.passport = passport;
      registerEvent(new StudentCreatedEvent(this));
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
    registerEvent(new StudentChangedInfoEvent(this));
    log.info("СНИЛС изменен");
  }

  /**
   * Замена свидетельства о рождении.
   *
   * @param birthCertificate день рождения
   */
  public void changeBirthCertificate(BirthCertificate birthCertificate) {
    this.birthCertificate = birthCertificate;
    registerEvent(new StudentChangedInfoEvent(this));
    log.info("Свидетельство о рождении успешно изменено");
  }

  /**
   * Добавление паспорта.
   *
   * @param passport паспорт
   */
  public void addPassport(Passport passport) {
    this.passport = passport;
    registerEvent(new StudentChangedInfoEvent(this));
    log.info("Паспорт успешно добавлен");
  }
}