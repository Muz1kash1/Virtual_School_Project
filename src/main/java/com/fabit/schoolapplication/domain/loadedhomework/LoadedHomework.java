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


  private LoadedHomework(LoadedHomeworkId loadedHomeworkId, StudentId studentId,
                         HomeworkForClassId homeworkForClassId) {
    this.studentId = studentId;
    this.loadedHomeworkId = loadedHomeworkId;
    this.homeworkForClassId = homeworkForClassId;
    registerEvent(new LoadedLoadedHomeworkCreatedEvent(this));
  }

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
    notNull(event, "Доменный ивент не должен быть нуль");
    domainEvents.add(event);
    return event;
  }

}
