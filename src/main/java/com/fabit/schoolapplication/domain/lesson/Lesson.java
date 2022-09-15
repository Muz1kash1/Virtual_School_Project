package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.lesson.event.HomeworkTaskSetEvent;
import com.fabit.schoolapplication.domain.lesson.event.LessonCreatedEvent;
import com.fabit.schoolapplication.domain.lesson.event.LessonEvent;
import com.fabit.schoolapplication.domain.teacher.TeacherId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Lesson {

  private TeacherId teacherId;
  private Discipline discipline;
  private String homeworkTask;

  public static final transient List<LessonEvent> domainEvents = new ArrayList<>();

  protected LessonEvent registerEvent(LessonEvent event) {
    Assert.notNull(event, "Доменный ивент не должен быть нуль");
    domainEvents.add(event);
    return event;
  }

  private Lesson(TeacherId teacherId, Discipline discipline) {
    this.teacherId = teacherId;
    this.discipline = discipline;
    registerEvent(new LessonCreatedEvent(this));
  }

  public static Lesson of(TeacherId teacherId, Discipline discipline) {
    return new Lesson(teacherId, discipline);
  }

  /**
   * Метод задания текста домашнего задания для всех прикрепленных к этому уроку студентов
   *
   * @param homeworkTask - текст домашнего задания
   */
  public void setHomeworkText(String homeworkTask) {
    this.homeworkTask = homeworkTask;
    registerEvent(new HomeworkTaskSetEvent(this));
  }

  /**
   * метод изменения назначенной на урок дисциплины
   * @param discipline дисциплина на которую переназначен урок
   */
  public void changeDiscipline(Discipline discipline){
    this.discipline = discipline;
  }

  /**
   * метод изменения назначенного на урок учителя
   * @param teacherId айди учителя на которого переназначается урок
   */
  public void changeTeacher(TeacherId teacherId){
    this.teacherId = teacherId;
  }
}
