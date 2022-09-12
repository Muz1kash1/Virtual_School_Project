package com.fabit.schoolapplication.domain.lesson;

import com.fabit.schoolapplication.domain.Discipline;
import com.fabit.schoolapplication.domain.TeacherId;
import com.fabit.schoolapplication.domain.lesson.event.HomeworkTaskSetEvent;
import com.fabit.schoolapplication.domain.lesson.event.LessonCreatedEvent;
import com.fabit.schoolapplication.domain.lesson.event.LessonEvent;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Lesson {

  private final TeacherId teacherId;

  private final Discipline discipline;

  private String homeworkTask;

  public static final transient List<LessonEvent> domainEvents = new ArrayList<>();

  protected LessonEvent registerEvent(LessonEvent event) {
    Assert.notNull(event,"Доменный ивент не должен быть нуль");
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
   * Метод задания текста домашнего задания для всех прикрепленных к этому уровку студентов
   *
   * @param homeworkTask - текст домашнего задания
   */
  public void setHomeworkText(String homeworkTask) {
    this.homeworkTask = homeworkTask;
    registerEvent(new HomeworkTaskSetEvent(this));
  }

}
