package com.fabit.schoolapplication.domain.loadedhomework;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedHomeworkEvent;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedLoadedHomeworkCreatedEvent;
import com.fabit.schoolapplication.domain.loadedhomework.event.LoadedLoadedHomeworkUpdatedEvent;
import com.fabit.schoolapplication.domain.student.StudentId;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Getter
public class LoadedHomework {

  public static final List<LoadedHomeworkEvent> domainEvents = new ArrayList<>();
  private final LoadedHomeworkId loadedHomeworkId;
  private final HomeworkForClassId homeworkForClassId;
  private final StudentId studentId;
  private String taskCompletionResult;


  public static final List<LoadedHomeworkEvent> DOMAIN_EVENTS = new ArrayList<>();

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
    registerEvent(new LoadedLoadedHomeworkUpdatedEvent(this));
  }

  protected LoadedHomeworkEvent registerEvent(LoadedHomeworkEvent event) {
    Assert.notNull(event, "Доменный ивент не должен быть нуль");
    DOMAIN_EVENTS.add(event);
    return event;
  }

}
