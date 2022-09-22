package com.fabit.schoolapplication.domain.teacher;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.RussianPassport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.event.TeacherActivatedDomainEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherCreatedDomainEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherDeactivatedDomainEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherDomainEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * Агрегат учителя.
 *
 * @author SmirnovMA
 */
@Getter
@Slf4j
@NoArgsConstructor
public class Teacher {

  public static final List<TeacherDomainEvent> DOMAIN_EVENTS = new ArrayList<>();

  private TeacherId teacherId;

  private int standingYears;

  private FullName fullName;

  private RussianPassport passport;

  private Snils snils;

  private boolean isActive;

  private Teacher(
      TeacherId teacherId,
      FullName fullName,
      RussianPassport passport,
      Snils snils,
      int standingYears,
      boolean isActive) {

    this.standingYears = standingYears;
    this.teacherId = teacherId;
    this.fullName = fullName;
    this.passport = passport;
    this.snils = snils;
    this.isActive = isActive;

    registerEvent(new TeacherCreatedDomainEvent(getTeacherId()));
  }

  /**
   * Статическая фабрика по созданию объектов учителя.
   *
   * @param teacherId     id учителя
   * @param fullName      Ф.И.О
   * @param passport      Паспорт
   * @param snils         СНИЛС
   * @param standingYears стаж работы
   * @return объект учителя
   */
  public static Teacher of(
      TeacherId teacherId,
      FullName fullName,
      RussianPassport passport,
      Snils snils,
      int standingYears,
      boolean active) {

    Teacher teacher = new Teacher(teacherId, fullName, passport, snils, standingYears, active);
    teacher.isActive = active;

    return teacher;
  }

  protected void registerEvent(TeacherDomainEvent event) {

    Assert.notNull(event, "Доменное событие не должно быть null");
    DOMAIN_EVENTS.add(event);
  }

  /**
   * Изменить статус учителя на неактивный.
   */
  public void deactivate(LocalDate from, LocalDate to) {

    if (isActive && from.isBefore(to)) {
      DOMAIN_EVENTS.clear();
      isActive = false;
      registerEvent(new TeacherDeactivatedDomainEvent(from, to, teacherId));
    } else {
      throw new IllegalStateException("Учитель уже деактивирован.");
    }

  }

  /**
   * Изменить статус учителя на активный.
   */
  public void activate() {

    if (!(isActive)) {
      isActive = true;
      DOMAIN_EVENTS.clear();
      registerEvent(new TeacherActivatedDomainEvent(teacherId));
    } else {
      throw new IllegalStateException(
          "Нельзя изменить статус учителя на АКТИВНЫЙ  так как он и так АКТИВНЫЙ");
    }

  }
}
