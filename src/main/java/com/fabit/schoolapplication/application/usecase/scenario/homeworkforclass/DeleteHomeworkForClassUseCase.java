package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.access.homeworkforclass.HomeworkForClassService;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteHomeworkForClassUseCase {

  private final HomeworkForClassService homeworkForClassService;

  /**
   * Метод удаления дз для класса.
   *
   * @param homeworkForClassId айди дз
   */
  public void execute(HomeworkForClassId homeworkForClassId) {
    homeworkForClassService.deleteById(homeworkForClassId);
  }
}
