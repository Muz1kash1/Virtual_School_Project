package com.fabit.schoolapplication.application.usecase.scenario.teacher;

import com.fabit.schoolapplication.application.usecase.access.teacher.TeacherService;
import com.fabit.schoolapplication.domain.teacher.Teacher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTeacher {

  private final TeacherService teacherService;

  /**
   * Создать учителя.
   *
   * @param teacher - доменная модель учителя
   * @return TeacherEntity
   */
  public Teacher execute(Teacher teacher) {
    teacherService.save(teacher);
    return teacher;
  }

}
