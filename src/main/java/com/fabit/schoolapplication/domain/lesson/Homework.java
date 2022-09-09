package com.fabit.schoolapplication.domain.lesson;

import java.io.File;
import lombok.Value;

@Value
public final class Homework {
  private final Long studentId;
  private final File completedHomework;
  private final String homeworkTask;

  private Homework(Long studentId, File completedHomework, String homeworkTask) {
    this.studentId = studentId;
    this.completedHomework = completedHomework;
    this.homeworkTask = homeworkTask;
  }

  public static Homework createHomework(
      Long studentId, File completedHomework, String homeworkTask) {
    return new Homework(studentId, completedHomework, homeworkTask);
  }
}
