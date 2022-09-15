package com.fabit.schoolapplication.domain.teacher;

import com.fabit.schoolapplication.domain.FullName;
import com.fabit.schoolapplication.domain.Passport;
import com.fabit.schoolapplication.domain.Snils;
import com.fabit.schoolapplication.domain.teacher.event.TeacherActivatedEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherCreatedEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherDeactivatedEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Агрегат учителя
 *
 * @author SmirnovMA
 */
@Getter
@Slf4j
public class Teacher {
  public static final List<TeacherEvent> domainEvents = new ArrayList<>();
  private TeacherId teacherId;
  /** стаж работы учителя */
  private int standingYears;
  /** Ф.И.О учителя */
  private FullName fullName;
  /** паспорт */
  private Passport passport;
  /** снилс */
  private Snils snils;
  /** статус учителя (можно ли его поставить на занятия или нет) */
  private boolean isActive;

  //  private boolean isDeleted;

  private Teacher() {}

  private Teacher(
      TeacherId teacherId,
      FullName fullName,
      Passport passport,
      Snils snils,
      int standingYears,
      boolean isActive) {
    this.standingYears = standingYears;
    this.teacherId = teacherId;
    this.fullName = fullName;
    this.passport = passport;
    this.snils = snils;
    this.isActive = isActive;
    registerEvent(new TeacherCreatedEvent(this.getTeacherId()));
    //    log.info("Учитель с id: " + teacherId.getValue() + " создан");
  }

  /**
   * Статическая фабрика по созданию объектов учителя
   *
   * @param teacherId id учителя
   * @param fullName Ф.И.О
   * @param passport Паспорт
   * @param snils СНИЛС
   * @param standingYears стаж работы
   * @return объект учителя
   */
  public static Teacher of(
      TeacherId teacherId,
      FullName fullName,
      Passport passport,
      Snils snils,
      int standingYears,
      boolean active) {
    Teacher teacher = new Teacher(teacherId, fullName, passport, snils, standingYears, active);
    teacher.isActive = active;
    return teacher;
  }

  protected void registerEvent(TeacherEvent event) {
    Assert.notNull(event, "Доменное событие не должно быть null");
    this.domainEvents.add(event);
  }

  /** Изменить статус учителя на неактивный */
  public void deactivate(LocalDate from, LocalDate to) {
    if (this.isActive && from.isBefore(to)) {
      domainEvents.clear();
      this.isActive = false;
      registerEvent(new TeacherDeactivatedEvent(from, to, teacherId));
      //      log.info(
      //          "Учитель с id: "
      //              + teacherId.getValue()
      //              + " отсутствует с "
      //              + from.toString()
      //              + " по "
      //              + to.toString());
    } else {
      throw new IllegalStateException(
          " Ошибка изменения статуса учителя, проверьте текущий статус учителя,"
              + "если он уже активный то не следует его снова активировать."
              + "Проверьте корректность введенных дат: первая дата должна быть раньше по времени чем вторая");
    }
  }
  /** Изменить статус учителя на активный */
  public void activate() {
    if (!(this.isActive)) {
      this.isActive = true;
      domainEvents.clear();
      //      log.info("Учитель с id: " + teacherId.getValue() + " снова активен");
      registerEvent(new TeacherActivatedEvent(this.teacherId));
    } else {
      throw new IllegalStateException(
          "Нельзя изменить статус учителя на АКТИВНЫЙ  так как он и так АКТИВНЫЙ");
    }
  }

  //  public void delete() {
  //    this.isDeleted = true;
  //  }
}
