package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import com.fabit.schoolapplication.infrastructure.persisnence.entity.teacher.TeacherEntity;
import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetTeacher {
  private final TeacherRepository teacherRepository;

  public TeacherEntity byId(long id) {
    return teacherRepository.findById(id).get();
  }

  public List<TeacherEntity> all() {
    return teacherRepository.findAll();
  }
}
