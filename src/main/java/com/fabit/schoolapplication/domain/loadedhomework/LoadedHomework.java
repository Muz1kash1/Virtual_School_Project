package com.fabit.schoolapplication.domain.loadedhomework;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedHomeworkCreatedEvent;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedHomeworkEvent;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedHomeworkUpdatedEvent;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter

public class LoadedHomework {

  public static final List<LoadedHomeworkEvent> DOMAIN_EVENTS = new ArrayList<>();
  private final LoadedHomeworkId loadedHomeworkId;
  private final HomeworkForClassId homeworkForClassId;
  private final StudentId studentId;
  private String taskCompletionResult;

  /**
   * Приватный конструктор для фабрики.
   *
   * @param loadedHomeworkId   - идентификатор загруженного ДЗ
   * @param studentId          - идентификатор ученика
   * @param homeworkForClassId - идентификатор ДЗ для школьного класса
   */
  private LoadedHomework(LoadedHomeworkId loadedHomeworkId,
                        StudentId studentId, HomeworkForClassId homeworkForClassId) {
    this.loadedHomeworkId = loadedHomeworkId;
    this.homeworkForClassId = homeworkForClassId;
    this.studentId = studentId;
    DOMAIN_EVENTS.add(new LoadedHomeworkCreatedEvent(this));

  }

  /**
   * Factory method.
   *
   * @param loadedHomeworkId   - идентификатор загруженного ДЗ
   * @param studentId          - идентификатор ученика
   * @param homeworkForClassId - идентификатор ДЗ для школьного класса
   * @return LoadedHomework
   */
  public static LoadedHomework of(LoadedHomeworkId loadedHomeworkId, StudentId studentId,
                                  HomeworkForClassId homeworkForClassId) {
    return new LoadedHomework(loadedHomeworkId, studentId,
      homeworkForClassId
    );

  }

  /**
   * Загрузить выполнение домашки.
   *
   * @param taskCompletionResult выполнение домашки
   */
  public void uploadTaskCompletionResult(String taskCompletionResult) {
    this.taskCompletionResult = taskCompletionResult;
    registerEvent(new LoadedHomeworkUpdatedEvent(this));
  }

  protected LoadedHomeworkEvent registerEvent(LoadedHomeworkEvent event) {
    Assert.notNull(event, "Доменный ивент не должен быть нуль");
    DOMAIN_EVENTS.add(event);
    return event;
  }

}
