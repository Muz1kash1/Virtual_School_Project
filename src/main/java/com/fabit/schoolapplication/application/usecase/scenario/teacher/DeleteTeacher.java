package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.application.usecase.access.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DeleteTeacher {
  private final TeacherService teacherService;


  /**
   * Удалить учителя по идентификатору.
   *
   * @param teacherId - идентификатор учителя
   */
  public void execute(long teacherId) {
    teacherService.deleteById(teacherId);
    log.info("Учитель (id" + teacherId + ") удалён из БД.");
  }
}
