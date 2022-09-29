package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.access.homeworkforclass.HomeworkForClassService;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangeHomeworkTaskUseCase {

  private final HomeworkForClassService homeworkForClassService;

  /**
   * Метод меняющий домашнее задание.
   *
   * @param id айди дз где оно меняется
   * @param task новое задание
   */
  public void execute(HomeworkForClassId id, String task) {
    homeworkForClassService.changeHomework(id, task);
  }
}
