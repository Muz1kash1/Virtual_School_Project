package com.fabit.schoolapplication.application.usecase.scenario.homeworkforclass;

import com.fabit.schoolapplication.application.usecase.access.homeworkforclass.HomeworkForClassService;
import com.fabit.schoolapplication.domain.generalvalueobject.discipline.Discipline;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClass;
import com.fabit.schoolapplication.domain.homeworkforclass.HomeworkForClassId;
import com.fabit.schoolapplication.domain.schoolclass.SchoolClassId;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateHomeworkForClassUseCase {

  private final HomeworkForClassService homeworkForClassService;

  /**
   * Метод создающий дз для класса.
   *
   * @param discipline    дисциплина
   * @param date          дата сдачи
   * @param schoolClassId класс
   */
  public void execute(Discipline discipline, LocalDate date, SchoolClassId schoolClassId) {

    HomeworkForClass homeworkForClass = HomeworkForClass.of(
        discipline, date, schoolClassId,
        HomeworkForClassId.of(1L)
    );

    homeworkForClassService.save(homeworkForClass);
  }

  /**
   * Метод создающий дз для класса из дто.
   *
   * @param homeworkForClass дто
   */
  public void execute(HomeworkForClass homeworkForClass) {
    homeworkForClassService.save(homeworkForClass);
  }

}
