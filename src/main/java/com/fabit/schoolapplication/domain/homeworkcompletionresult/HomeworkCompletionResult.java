package com.fabit.schoolapplication.domain.homeworkcompletionresult;

import com.fabit.schoolapplication.domain.LessonId;
import com.fabit.schoolapplication.domain.StudentId;
import com.fabit.schoolapplication.domain.TeacherId;
import com.fabit.schoolapplication.domain.homeworkcompletionresult.event.HomeworkCompletionCreatedEvent;
import com.fabit.schoolapplication.domain.homeworkcompletionresult.event.HomeworkCompletionEvent;
import com.fabit.schoolapplication.domain.homeworkcompletionresult.event.HomeworkCompletionUpdatedEvent;
import com.fabit.schoolapplication.domain.lesson.event.LessonEvent;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class HomeworkCompletionResult {

  private final TeacherId teacherId;
  private final StudentId studentId;
  private String taskCompletionResult;

  private final LessonId lessonId;

  public static final transient List<HomeworkCompletionEvent> domainEvents = new ArrayList<>();


  /**
   * загрузить выполнение домашки
   *
   * @param taskCompletionResult выполнение домашки
   */
  public void uploadTaskCompletionResult(String taskCompletionResult) {
    this.taskCompletionResult = taskCompletionResult;
    registerEvent(new HomeworkCompletionUpdatedEvent(this));
  }

  protected HomeworkCompletionEvent registerEvent(HomeworkCompletionEvent event) {
    Assert.notNull(event,"Доменный ивент не должен быть нуль");
    domainEvents.add(event);
    return event;
  }

  private HomeworkCompletionResult(StudentId studentId, TeacherId teacherId, LessonId lessonId) {
    this.teacherId = teacherId;
    this.studentId = studentId;
    this.lessonId = lessonId;
    registerEvent(new HomeworkCompletionCreatedEvent(this));
  }

  public static HomeworkCompletionResult of(TeacherId teacherId, StudentId studentId,
      LessonId lessonId) {
    return new HomeworkCompletionResult(studentId,
        teacherId, lessonId);
  }

}
