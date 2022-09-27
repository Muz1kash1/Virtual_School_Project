package com.fabit.schoolapplication.domain.homeworkforclass;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.event.HomeworkForClassCreatedEvent;
import com.fabit.schoolapplication.domain.homeworkforclass.event.HomeworkForClassEvent;
import com.fabit.schoolapplication.domain.homeworkforclass.event.HomeworkForClassTaskSetEvent;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;



@Getter
public class HomeworkForClass {

  private HomeworkForClassId id;

  private Discipline discipline;

  private String task;

  private SchoolClassId schoolClassId;

  private final LocalDate date;

  public static final transient List<HomeworkForClassEvent> DOMAIN_EVENTS = new ArrayList<>();

  protected HomeworkForClassEvent registerEvent(HomeworkForClassEvent event) {
    DOMAIN_EVENTS.add(event);
    return event;
  }

  private HomeworkForClass(Discipline discipline, LocalDate date) {
    this.discipline = discipline;
    this.date = date;
    registerEvent(new HomeworkForClassCreatedEvent(this));
  }

  /**
   * Factory method - Создание ДЗ для класса.
   *
   * @param discipline    - дисциплина
   * @param date          - дата
   * @param schoolClassId - идентификатор класса
   * @param id            - идентификатор домашнего задания
   * @return HomeworkForClass
   */
  public static HomeworkForClass of(
      Discipline discipline, LocalDate date, SchoolClassId schoolClassId, HomeworkForClassId id) {

    HomeworkForClass homeworkForClass = new HomeworkForClass(discipline, date);
    homeworkForClass.schoolClassId = schoolClassId;
    homeworkForClass.id = id;

    return homeworkForClass;
  }

  /**
   * Метод задания текста домашнего задания для всех прикрепленных к этому уроку студентов.
   *
   * @param homeworkTask - текст домашнего задания
   */
  public void setHomeworkText(String homeworkTask) {
    task = homeworkTask;
    registerEvent(new HomeworkForClassTaskSetEvent(this));
  }

  /**
   * Метод изменения назначенной на урок дисциплины.
   *
   * @param discipline дисциплина на которую переназначен урок
   */
  public void changeDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }
}

