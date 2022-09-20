package com.fabit.schoolapplication.domain.homework;

import com.fabit.schoolapplication.domain.homework.event.HomeworkCreatedEvent;
import com.fabit.schoolapplication.domain.homework.event.HomeworkEvent;
import com.fabit.schoolapplication.domain.homework.event.HomeworkUpdatedEvent;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.student.StudentId;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Homework {

  private final HomeworkId homeworkId;
  private final HomeworkForClassId homeworkForClassId;
  private final StudentId studentId;
  private String taskCompletionResult;


  public static final transient List<HomeworkEvent> domainEvents = new ArrayList<>();


  /**
   * загрузить выполнение домашки
   *
   * @param taskCompletionResult выполнение домашки
   */
  public void uploadTaskCompletionResult(String taskCompletionResult) {
    this.taskCompletionResult = taskCompletionResult;
    registerEvent(new HomeworkUpdatedEvent(this));
  }

  protected HomeworkEvent registerEvent(HomeworkEvent event) {
    Assert.notNull(event, "Доменный ивент не должен быть нуль");
    domainEvents.add(event);
    return event;
  }

  private Homework(HomeworkId homeworkId, StudentId studentId,
      HomeworkForClassId homeworkForClassId) {
    this.studentId = studentId;
    this.homeworkId = homeworkId;
    this.homeworkForClassId = homeworkForClassId;
    registerEvent(new HomeworkCreatedEvent(this));
  }

  public static Homework of(HomeworkId homeworkId, StudentId studentId,
      HomeworkForClassId homeworkForClassId) {
    return new Homework(homeworkId, studentId,
        homeworkForClassId);
  }

}
