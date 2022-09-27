package com.fabit.schoolapplication.domain.teacher;

import com.fabit.schoolapplication.domain.generalvalueobject.fullname.FullName;
import com.fabit.schoolapplication.domain.generalvalueobject.passportvo.impl.RussianPassport;
import com.fabit.schoolapplication.domain.generalvalueobject.snils.Snils;
import com.fabit.schoolapplication.domain.teacher.event.TeacherActivatedDomainEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherCreatedDomainEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherDeactivatedDomainEvent;
import com.fabit.schoolapplication.domain.teacher.event.TeacherDomainEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Агрегат учителя.
 *
 * @author SmirnovMA
 */
@Getter
@Slf4j
public class Teacher {

  public static final List<TeacherDomainEvent> DOMAIN_EVENTS = new ArrayList<>();

  private TeacherId id;

  /**
   * Ф.И.О учителя.
   */
  private FullName fullName;

  /**
   * Паспорт.
   */
  private RussianPassport passport;

  /**
   * Снилс.
   */
  private Snils snils;

  /**
   * Статус учителя (можно ли его поставить на занятия или нет).
   */
  private boolean isActive;

  private Teacher() {
  }

  private Teacher(TeacherId id, FullName fullName,
                  RussianPassport passport, Snils snils) {
    this.id = id;
    this.fullName = fullName;
    this.passport = passport;
    this.snils = snils;
    this.isActive = true;
    registerEvent(new TeacherCreatedDomainEvent(this.getId()));
  }

  /**
   * Статическая фабрика по созданию объектов учителя.
   *
   * @param teacherId id учителя
   * @param fullName  Ф.И.О
   * @param passport  Паспорт
   * @param snils     СНИЛС
   * @return объект учителя
   */
  public static Teacher of(TeacherId teacherId, FullName fullName,
                           RussianPassport passport, Snils snils) {
    return new Teacher(teacherId, fullName, passport, snils);
  }

  public static Teacher copyOf(TeacherId teacherId, FullName fullName, RussianPassport passport,
                               Snils snils, boolean isActive) {

    Teacher teacher = new Teacher();
    teacher.id = teacherId;
    teacher.fullName = fullName;
    teacher.passport = passport;
    teacher.snils = snils;
    teacher.isActive = isActive;
    return teacher;
  }

  protected void registerEvent(TeacherDomainEvent event) {
    if (event != null) {
      DOMAIN_EVENTS.add(event);
    } else {
      throw new NullPointerException("Ивент не должен быть пустым!");
    }
  }

  /**
   * Изменить статус учителя на неактивный.
   */
  public void deactivate(LocalDate from, LocalDate to) {
    if (this.isActive && from.isBefore(to)) {
      this.isActive = false;
      registerEvent(new TeacherDeactivatedDomainEvent(from, to, id));
    } else {
      throw new IllegalStateException(
          " Ошибка изменения статуса учителя, проверьте текущий статус учителя,"
              + "если он уже активный то не следует его снова активировать."
              + "Проверьте корректность введенных дат: "
              + "первая дата должна быть раньше по времени чем вторая");
    }
  }

  /**
   * Изменить статус учителя на активный.
   */
  public void activate() {
    if (!(this.isActive)) {
      this.isActive = true;
      registerEvent(new TeacherActivatedDomainEvent(this.id));
    } else {
      throw new IllegalStateException(
          "Нельзя изменить статус учителя на АКТИВНЫЙ  так как он и так АКТИВНЫЙ");
    }
  }
}
