package com.fabit.schoolapplication.infrastructure.usecase.teacher;

import com.fabit.schoolapplication.infrastructure.persisnence.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteTeacher {
  private final TeacherRepository teacherRepository;

  @Transactional
  public void execute(long teacherId) {
    teacherRepository.deleteById(teacherId);
  }
}
