package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.application.usecase.access.teacher.TeacherService;
import com.fabit.schoolapplication.infrastructure.ui.controller.teacher.dto.DeactivateDto;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;


@Slf4j
@RequiredArgsConstructor
public class EditTeacher {

  private final TeacherService teacherService;


  /**
   * Активировать учителя (вышел на работу).
   *
   * @param teacherId - идентификатор учителя
   * @return TeacherEntity
   */

  public Teacher activate(long teacherId) {

    Teacher teacher = teacherService.findById(teacherId);
    teacher.activate();
    teacherService.save(teacher);
    log.info("Учитель активирован и вновь может работать: " + teacher);
    return teacher;
  }

  /**
   * Деактивировать учителя (более не может работать).
   *
   * @param deactivateDto - ДТО деактивации учителя
   * @return TeacherEntity
   */
  public Teacher deactivate(DeactivateDto deactivateDto) {

    Teacher teacher
      = teacherService.findById(deactivateDto.getTeacherId());

    teacher.deactivate(
      LocalDate.parse(deactivateDto.getFrom()), LocalDate.parse(deactivateDto.getTo()));

    teacherService.save(teacher);

    log.info("Учитель деактивирован и не может работать: " + teacher);
    return teacher;
  }
}
