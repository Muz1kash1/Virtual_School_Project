package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.access.homeworkforclass.HomeworkForClassService;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetHomeworkForClassUseCase {

  private final HomeworkForClassService homeworkForClassService;

  /**
   * Метод возвращающий дз класса по идентификатору.
   *
   * @param homeworkForClassId айди
   * @return дз по этому айди
   */
  public HomeworkForClass execute(HomeworkForClassId homeworkForClassId) {
    return homeworkForClassService.findById(homeworkForClassId);
  }
}
