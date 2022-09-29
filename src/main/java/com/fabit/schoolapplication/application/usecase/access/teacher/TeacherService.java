package com.fabit.schoolapplication.application.usecase.access.teacher;

import com.fabit.schoolapplication.domain.teacher.Teacher;
import java.util.List;

public interface TeacherService {

  void save(Teacher teacher);

  void deleteById(long teacherId);

  Teacher findById(long teacherId);

  List<Teacher> findAll();

}
