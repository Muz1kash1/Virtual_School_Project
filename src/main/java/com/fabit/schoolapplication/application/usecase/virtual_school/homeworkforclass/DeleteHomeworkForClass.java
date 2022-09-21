package com.fabit.schoolapplication.application.usecase.virtual_school.homeworkforclass;

import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.HomeworkForClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteHomeworkForClass {

  private final HomeworkForClassRepository homeworkForClassRepository;

  /**
   * Метод удаления дз для класса.
   *
   * @param homeworkForClassId айди дз
   */
  public void execute(HomeworkForClassId homeworkForClassId) {
    homeworkForClassRepository.deleteById(homeworkForClassId.getValue());
  }
}
