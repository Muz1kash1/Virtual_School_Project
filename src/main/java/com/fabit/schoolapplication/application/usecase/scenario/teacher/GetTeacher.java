package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.application.usecase.access.teacher.TeacherService;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetTeacher {

  private final TeacherService teacherService;

  /**
   * Получить учителя по идентификатору.
   *
   * @param id - идентификатор учителя
   * @return TeacherEntity
   */
  public Teacher byId(long id) {
    return teacherService.findById(id);
  }

  public List<Teacher> all() {
    return teacherService.findAll();
  }

}
