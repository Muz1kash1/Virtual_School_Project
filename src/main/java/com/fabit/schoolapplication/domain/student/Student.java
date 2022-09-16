package com.fabit.schoolapplication.domain.student;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.student.event.StudentChangedInfoEvent;
import com.fabit.schoolapplication.domain.student.event.StudentCreatedEvent;
import com.fabit.schoolapplication.domain.student.event.StudentDomainEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
  private FullName fullName;
  private Snils snils;
  private BirthCertificate birthCertificate;
  private Passport passport;
  private LocalDate birthday;
  public static final List<StudentDomainEvent> domainEvents = new ArrayList<>();

  protected void registerEvent(StudentDomainEvent event) {
    Assert.notNull(event, "Domain event must not be null");
    this.domainEvents.add(event);
  }

  private Student(StudentId studentId, FullName name, Snils snils,
                  BirthCertificate birthCertificate, String birthday) {
    this.studentId = studentId;
    this.fullName = name;
    this.snils = snils;
    this.birthCertificate = birthCertificate;
    this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    registerEvent(new StudentCreatedEvent(this));
  }

  private Student(StudentId studentId, FullName name, Snils snils, Passport passport,
                  String birthday) {
    this.studentId = studentId;
    this.fullName = name;
    this.snils = snils;
    this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    if (passport.isValidAge(this.birthday)) {
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
  public static Student of(StudentId studentId, FullName name, Snils snils,
                           BirthCertificate birthCertificate, String birthday) {
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
  public static Student of(StudentId studentId, FullName name, Snils snils, Passport passport,
                           String birthday) {
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